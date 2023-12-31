package com.ceiling45688.controller;

import com.ceiling45688.dto.FilterRequestDTO;
import com.ceiling45688.dto.ReservationRequestDTO;
import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;
import com.ceiling45688.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/available")
    public ResponseEntity<List<String>> listAvailableApartments(){
        List<String> availableApartments = apartmentService.listAvailableApartments();
        return ResponseEntity.ok(availableApartments);
    }

    @PostMapping("/{apartmentNumber}/reserve")
    public ResponseEntity<Apartment> reserveApartment(@PathVariable String apartmentNumber, @RequestBody ReservationRequestDTO request){
        try {
            Apartment reservedApartment = apartmentService.reserveApartment(request.getUserId(),
                    apartmentNumber,
                    request.getRoomNumber(),
                    request.getStartDate(),
                    request.getEndDate());
            return new ResponseEntity<>(reservedApartment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


    //接受筛选条件并返回满足条件的房间列表。
    @PostMapping("/rooms/filtered")
    public ResponseEntity<List<Room>> listFilteredRooms(@RequestBody FilterRequestDTO filterRequest){
        List<Room> filteredRooms = apartmentService.listFilteredRooms(filterRequest.getRoomType(), filterRequest.getStartDate(), filterRequest.getEndDate());
        return ResponseEntity.ok(filteredRooms);
    }



}
