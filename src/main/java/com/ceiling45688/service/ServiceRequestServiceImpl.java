package com.ceiling45688.service;

import com.ceiling45688.model.Room;
import com.ceiling45688.model.ServiceRequest;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.repository.ServiceRequestRepository;
import com.ceiling45688.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public ServiceRequest createServiceRequest(Long userId, String description, Room room){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setUser(user);
        serviceRequest.setRoom(room);
        serviceRequest.initial();

        return serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public ServiceRequest updateServiceRequest(Long serviceRequestId, String action){
        ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Service request not found"));

        switch (action.toLowerCase()){
            case "initial":
                serviceRequest.initial();
                break;
            case "start":
                serviceRequest.start();
                break;
            case "end":
                serviceRequest.end();
                break;
            default:
                throw new IllegalArgumentException("Invalid action provided");
        }
        return serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public List<ServiceRequest> getServiceRequestsByUser(Long userId) {
        return serviceRequestRepository.findByUserId(userId);
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
        // 使用JPA的findAll方法获取所有服务请求
        return serviceRequestRepository.findAll();
    }

    @Override
    public void batchUpdateServiceRequests(List<ServiceRequest> updatedRequests) {
        // 使用JPA的saveAll方法批量更新服务请求
        serviceRequestRepository.saveAll(updatedRequests);
    }

}
