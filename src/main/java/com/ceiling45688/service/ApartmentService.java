package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ApartmentService {

    List<String> listAvailableApartments();
    Apartment reserveApartment(Long userId, String apartmentNumber, String roomNumber,
                               Date startDate, Date endDate);
    Optional<Apartment> findByApartmentNumber(String apartmentNumber);
    Optional<Room> findRoomByApartmentNumberAndRoomNumber(String apartmentNumber, String roomNumber);

    public List<Room> listFilteredRooms(String roomType, Date startDate, Date endDate);



        // other functional methods
    boolean isApartmentNumberValid(String apartmentNumber);
    boolean isRoomNumberValid(String roomNumber);

}
