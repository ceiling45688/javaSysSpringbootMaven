package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public List<String> listAvailableApartments(){

        return ;
    }

    @Override
    public Apartment reserveApartment()
}
