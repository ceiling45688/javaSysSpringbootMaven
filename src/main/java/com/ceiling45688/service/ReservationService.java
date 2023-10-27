package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Reservation;
import com.ceiling45688.model.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation createReservation(Long userId, Apartment apartment, Room room, Date startDate, Date endDate);
    void cancelReservation(Long reservationId);
    Optional<Reservation> getReservationByUser(Long userId);
}
