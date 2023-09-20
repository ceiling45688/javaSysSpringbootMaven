package com.ceiling45688.repository;

import com.ceiling45688.model.Reservation;
import com.ceiling45688.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserId(Long userId); //jpa会自动解析，这里是基于user实体的id属性查询

}
