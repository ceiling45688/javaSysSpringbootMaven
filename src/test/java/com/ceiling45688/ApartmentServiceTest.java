package com.ceiling45688;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;
import com.ceiling45688.repository.ApartmentRepository;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.service.ApartmentServiceImpl;
import com.ceiling45688.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApartmentServiceTest {

    //自动注入被测试的服务类实例
    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    // 模拟依赖项
    @Mock
    private ApartmentRepository apartmentRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationService reservationService;

    // 初始化模拟对象
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAvailableApartments() {
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("A101");
        Room room = new Room();
        room.setStatus(Room.RoomStatus.AVAILABLE);
        apartment.setRooms(Arrays.asList(room));

        when(apartmentRepository.findAll()).thenReturn(Arrays.asList(apartment));

        List<String> result = apartmentService.listAvailableApartments();

        assertEquals(1, result.size());
        assertEquals("A101", result.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReserveApartment_ApartmentNotFound() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.empty());

        apartmentService.reserveApartment(1L, "A101", "R101", new Date(), new Date());
    }

}
