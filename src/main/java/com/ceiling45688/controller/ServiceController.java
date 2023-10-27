package com.ceiling45688.controller;

import com.ceiling45688.dto.ServiceRequestDTO;
import com.ceiling45688.model.Room;
import com.ceiling45688.model.ServiceRequest;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ServiceController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping("/service-requests")
    public ResponseEntity<ServiceRequest> createServiceRequest(@RequestBody ServiceRequestDTO request) {
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        ServiceRequest serviceRequest = serviceRequestService.createServiceRequest(request.getUserId(), request.getDescription(), room);
        return ResponseEntity.ok(serviceRequest);
    }
}
