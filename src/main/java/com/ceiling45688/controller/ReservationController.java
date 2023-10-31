package com.ceiling45688.controller;


import com.ceiling45688.dto.ReservationRequestDTO;
import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Reservation;
import com.ceiling45688.model.Room;
import com.ceiling45688.service.ApartmentService;
import com.ceiling45688.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ApartmentService apartmentService;


    @PostMapping("/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO request) {
        // 1. Retrieve the Apartment object using the apartmentNumber from the DTO
        Optional<Apartment> apartmentOptional = apartmentService.findByApartmentNumber(request.getApartmentNumber());

        // check if optional is empty -- apartment not found
        if (!apartmentOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid apartment number");// 错误检查
        }

        // 2. Retrieve the Room object using the apartmentNumber and roomNumber from the DTO
        Optional<Room> roomOptional = apartmentService.findRoomByApartmentNumberAndRoomNumber(request.getApartmentNumber(), request.getRoomNumber());
        if (!roomOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid room number");
        }

        // If the Optional has a value, retrieve the Apartment and Room object
        Apartment apartment = apartmentOptional.get();
        Room room = roomOptional.get();

        // 3. Call the service method using the retrieved objects and other fields from the DTO
        Reservation reservation = reservationService.createReservation(request.getUserId(), apartment, room, request.getStartDate(), request.getEndDate());

        return new ResponseEntity<>(reservation, HttpStatus.CREATED);

    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void>  cancelReservation(@PathVariable Long reservationId){
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Reservation> getReservationByUser(@PathVariable Long userId){
        Optional<Reservation> optionalReservation = reservationService.getReservationByUser(userId);

        if (optionalReservation.isPresent()) {
            return new ResponseEntity<>(optionalReservation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
