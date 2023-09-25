package com.ceiling45688;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Reservation;
import com.ceiling45688.model.Room;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.ReservationRepository;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.repository.UserRepository;
import com.ceiling45688.service.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationServiceTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    // 初始化模拟对象
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateReservation(){
        Long userId = 1L;
        User user = new User();
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("20");
        Room room = new Room();
        room.setRoomNumber("1");
        apartment.setRooms(Arrays.asList(room));


        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        reservationService.createReservation(userId,apartment, room,new Date(), new Date());

        // 检查save方法是否被调用以及调用此时
        verify(roomRepository, times(1)).save(room);
        verify(reservationRepository, times(1)).save(any(Reservation.class));

        // 验证房间状态是否已经修改
        assertEquals(Room.RoomStatus.OCCUPIED, room.getStatus());
    }

    @Test
    public void testCancelReservation(){

        User user = new User();
        Apartment apartment = new Apartment();
        Room room = new Room("1");
        apartment.setRooms(Arrays.asList(room));
        room.setStatus(Room.RoomStatus.OCCUPIED);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setApartment(apartment);
        reservation.setRoom(room);

        Long reservationId = reservation.getId();

        // 验证findById
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // 使用verify确保roomRepository.save和reservationRepository.delete被正确调用
        reservationService.cancelReservation(reservationId);
        verify(roomRepository, times(1)).save(room);
        verify(reservationRepository, times(1)).delete(reservation);

        // 验证房间状态是否被修改
        assertEquals(Room.RoomStatus.AVAILABLE, room.getStatus());

    }

}
