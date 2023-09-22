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

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
        apartment.setApartmentNumber("1"); // apart from 1 - 20
        Room room = new Room();
        room.setStatus(Room.RoomStatus.AVAILABLE);
        apartment.setRooms(Arrays.asList(room));

        when(apartmentRepository.findAll()).thenReturn(Arrays.asList(apartment));

        List<String> result = apartmentService.listAvailableApartments();

        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReserveApartment_ApartmentNotFound() {
        when(apartmentRepository.findByApartmentNumber(anyString())).thenReturn(Optional.empty());

        apartmentService.reserveApartment(1L, "A101", "R101", new Date(), new Date());
    }

    // 当所有房间都被预定时，公寓应该被视为不可用
    @Test
    public void testAllRoomsOccupied(){
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("1");

        Room room1 = new Room();
        room1.setStatus(Room.RoomStatus.OCCUPIED);
        room1.setRoomNumber("1");
        Room room2 = new Room();
        room2.setStatus(Room.RoomStatus.OCCUPIED);
        room2.setRoomNumber("2");
        Room room3 = new Room();
        room3.setStatus(Room.RoomStatus.OCCUPIED);
        room3.setRoomNumber("3");
        Room room4 = new Room();
        room4.setStatus(Room.RoomStatus.OCCUPIED);
        room4.setRoomNumber("4");
        Room room5 = new Room();
        room5.setStatus(Room.RoomStatus.OCCUPIED);
        room5.setRoomNumber("5");
        Room room6 = new Room();
        room6.setStatus(Room.RoomStatus.OCCUPIED);
        room6.setRoomNumber("6");

        apartment.setRooms(Arrays.asList(room1,room2,room3,room4,room5,room6));

        //Mockito 模拟对象行为，无需实际数据库网络或其他外部依赖
        when(apartmentRepository.findAll()).thenReturn(Arrays.asList(apartment));//返回上述创建的公寓列表
        //调用listAvailableApartments方法并检查结果, 结果应该没有可用公寓
        List<String> result = apartmentService.listAvailableApartments();

        assertTrue(result.isEmpty());

    }

    // 当至少有一个房间可用的时候，公寓应该被视为可用
    @Test
    public void testAtLeastOneRoomAvailable(){
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("1");

        Room room1 = new Room();
        room1.setStatus(Room.RoomStatus.AVAILABLE);
        room1.setRoomNumber("1");
        Room room2 = new Room();
        room2.setStatus(Room.RoomStatus.OCCUPIED);
        room2.setRoomNumber("2");
        Room room3 = new Room();
        room3.setStatus(Room.RoomStatus.OCCUPIED);
        room3.setRoomNumber("3");
        Room room4 = new Room();
        room4.setStatus(Room.RoomStatus.OCCUPIED);
        room4.setRoomNumber("4");
        Room room5 = new Room();
        room5.setStatus(Room.RoomStatus.OCCUPIED);
        room5.setRoomNumber("5");
        Room room6 = new Room();
        room6.setStatus(Room.RoomStatus.OCCUPIED);
        room6.setRoomNumber("6");

        apartment.setRooms(Arrays.asList(room1,room2,room3,room4,room5,room6));

        //返回上述创建的公寓列表
        when(apartmentRepository.findAll()).thenReturn(Arrays.asList(apartment));
        //调用listAvailableApartments方法并检查结果, 结果应该1公寓可以用,大小为1
        List<String> result = apartmentService.listAvailableApartments();

        assertEquals(1,result.size());
        assertEquals("1", result.get(0));
    }

    // 测试预定已经被预定的房间, 其实按理说在前端根本不会显示, 后面记得处理
    @Test(expected = IllegalStateException.class)
    public void testReserveOccupiedRoom(){
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("1");

        Room room1 = new Room();
        room1.setStatus(Room.RoomStatus.OCCUPIED);
        room1.setRoomNumber("1");
        apartment.setRooms(Arrays.asList(room1));

        //Optional.of参数必须非空，这里如果返回的apartment为null会抛出异常
        when(apartmentRepository.findByApartmentNumber("1")).thenReturn(Optional.of(apartment));
        //使用参数匹配器
        when(roomRepository.findByRoomNumberAndAndApartment(anyString(), any())).thenReturn(Optional.of(room1));

        apartmentService.reserveApartment(1L, "1", "1", new Date(), new Date());
    }

    // 测试invalid的公寓和房间
    @Test(expected = IllegalArgumentException.class)
    public void testReserveNonExistentApartment(){
        when(apartmentRepository.findByApartmentNumber("21")).thenReturn(Optional.empty());

        apartmentService.reserveApartment(1L,"21","1",new Date(), new Date());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testReserveNonExistentRoom(){
        Apartment apartment = new Apartment();
        apartment.setApartmentNumber("1");

        when(apartmentRepository.findByApartmentNumber("1")).thenReturn(Optional.of(apartment));
        when(roomRepository.findByRoomNumberAndAndApartment("7",apartment)).thenReturn(Optional.empty());

        apartmentService.reserveApartment(1L,"1","7",new Date(), new Date());
    }

    // 其实后面预定还应该加一个日期的测试？


}
