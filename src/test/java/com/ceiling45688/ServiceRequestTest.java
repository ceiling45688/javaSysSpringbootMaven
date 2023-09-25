package com.ceiling45688;


import com.ceiling45688.model.Room;
import com.ceiling45688.model.ServiceRequest;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.repository.ServiceRequestRepository;
import com.ceiling45688.repository.UserRepository;
import com.ceiling45688.service.ServiceRequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;



@SpringBootTest
public class ServiceRequestTest {

    @InjectMocks
    private ServiceRequestServiceImpl serviceRequestService;

    @Mock
    private ServiceRequestRepository serviceRequestRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoomRepository roomRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    // 使用反射修改对象的私有字段(只在测试时用)
    public static void setId(Object entity, Long id) throws Exception {
        Field field = entity.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(entity, id);
    }

    @Test
    public void testCreateServiceRequest() throws Exception{
        User user = new User();
        setId(user, 1L);
        Long userId = user.getId();

        Room room = new Room("1");
        String description = "Oven of Room 20.1 is broken.";

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        // 模拟当save被调用时返回传递给它得那个servicerequest对象
        when(serviceRequestRepository.save(any(ServiceRequest.class))).thenAnswer(invocation -> invocation.getArgument(0));


        ServiceRequest result = serviceRequestService.createServiceRequest(userId,description,room);
//
        // 验证状态是否为pending
        assertEquals(ServiceRequest.Status.PENDING, result.getStatus());
        assertEquals(user, result.getUser());
        assertEquals(room, result.getRoom());
        verify(serviceRequestRepository, times(1)).save(any(ServiceRequest.class));
    }

    @Test
    public void testUpdateServiceRequest() throws Exception{
        ServiceRequest serviceRequest = new ServiceRequest();
        setId(serviceRequest, 1L);
        Long requestId = serviceRequest.getId();

        serviceRequest.initial();

        when(serviceRequestRepository.findById(requestId)).thenReturn(Optional.of(serviceRequest));
        when(serviceRequestRepository.save(any(ServiceRequest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ServiceRequest result = serviceRequestService.updateServiceRequest(requestId, "start");

        assertEquals(ServiceRequest.Status.IN_PROGRESS, result.getStatus());
        verify(serviceRequestRepository, times(1)).save(any(ServiceRequest.class));

    }




}
