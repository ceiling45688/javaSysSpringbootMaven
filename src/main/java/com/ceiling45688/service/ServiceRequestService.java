package com.ceiling45688.service;

import com.ceiling45688.model.Room;
import com.ceiling45688.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {
    ServiceRequest createServiceRequest(Long userId, String description, Room room);
    ServiceRequest updateServiceRequest(Long serviceRequestId, String action);
    List<ServiceRequest> getServiceRequestsByUser(Long userId);

    List<ServiceRequest> getAllServiceRequests();
    void batchUpdateServiceRequests(List<ServiceRequest> updatedRequests);

}
