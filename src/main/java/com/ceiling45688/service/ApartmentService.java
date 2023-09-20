package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;

import java.util.Date;
import java.util.List;

public interface ApartmentService {

    List<String> listAvailableApartments();
    Apartment reserveApartment(Long userId, String apartmentNumber, String roomNumber,
                                         Date startDate, Date endDate);
}
