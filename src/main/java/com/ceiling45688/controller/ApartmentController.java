package com.ceiling45688.controller;

import com.ceiling45688.dto.ReservationRequestDTO;
import com.ceiling45688.model.Apartment;
import com.ceiling45688.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/available")
    public List<String> listAvailableApartments(){
        return apartmentService.listAvailableApartments();
    }

    @PostMapping("/reserve")
    public Apartment reserveApartment(@RequestBody ReservationRequestDTO request){
        return apartmentService.reserveApartment(request.getUserId(),
                request.getApartmentNumber(),
                request.getRoomNumber(),
                request.getStartDate(),
                request.getEndDate());
    }
}
