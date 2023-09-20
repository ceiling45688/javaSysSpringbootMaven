package com.ceiling45688.repository;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumberAndAndApartment(String roomNumber, Apartment apartment);
}
