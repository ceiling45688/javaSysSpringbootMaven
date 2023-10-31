package com.ceiling45688.controller;

import com.ceiling45688.dto.ServiceRequestDTO;
import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;
import com.ceiling45688.model.ServiceRequest;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.service.ApartmentService;
import com.ceiling45688.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-requests")
public class ServiceController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequestDTO request) {
        // 拆分dto中的roomId为apartNumber和roomNumber
        String[] parts = request.getRoomId().split("\\.");
        String apartmentNumber = parts[0];
        String roomNumber = parts[1];

        // 验证公寓号和房间号的有效性
        if (!apartmentService.isApartmentNumberValid(apartmentNumber) || !apartmentService.isRoomNumberValid(roomNumber)) {
            throw new IllegalArgumentException("Invalid roomId provided");
        }

        // 查询公寓和房间
        Apartment apartment = apartmentService.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new IllegalArgumentException("Apartment not found"));
        Room room = apartmentService.findRoomByApartmentNumberAndRoomNumber(apartmentNumber, roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));


        ServiceRequest serviceRequest = serviceRequestService.createServiceRequest(request.getUserId(), request.getDescription(), room);
        return ResponseEntity.ok(serviceRequest);

    }

    // 更新服务请求状态，使用serviceRequestId路径参数和action请求参数，目前没有用到
    @PutMapping("/{serviceRequestId}")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable Long serviceRequestId, @RequestParam String action) {
        ServiceRequest updatedServiceRequest = serviceRequestService.updateServiceRequest(serviceRequestId, action);
        return ResponseEntity.ok(updatedServiceRequest);
    }

    // 批量更新服务请求
    @PutMapping("/batch-update")
    public ResponseEntity<Void> batchUpdateServiceRequests(@RequestBody List<ServiceRequest> updatedRequests) {
        serviceRequestService.batchUpdateServiceRequests(updatedRequests);
        return ResponseEntity.noContent().build();
    }

    // 获取特定用户的所有服务请求， 目前前端没有用到
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ServiceRequest>> getServiceRequestsByUser(@PathVariable Long userId) {
        List<ServiceRequest> serviceRequests = serviceRequestService.getServiceRequestsByUser(userId);
        return ResponseEntity.ok(serviceRequests);
    }

    // 获取所有用户的服务请求
    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();
        return ResponseEntity.ok(serviceRequests);
    }






}
