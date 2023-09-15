package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;

import java.util.List;

public interface ApartmentService {

    List<String> listAvaliableApartments();
    Apartment reserveApartment();
}
