package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Reservation;
import com.ceiling45688.model.Room;
import com.ceiling45688.model.User;
import com.ceiling45688.repository.ReservationRepository;
import com.ceiling45688.repository.RoomRepository;
import com.ceiling45688.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reservation createReservation(Long userId, Apartment apartment, Room room, Date startDate, Date endDate) {
        // 创建新的预订
        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found")));
        reservation.setApartment(apartment);
        reservation.setRoom(room);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        // 修改房间状态为OCCUPIED
        room.setStatus(Room.RoomStatus.OCCUPIED);
        roomRepository.save(room);

        // 保存预订到数据库
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId){
        // 这里的reservationId 等于 reservation实体中的id主键
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        Room room = reservation.getRoom();

        // 修改房间状态为AVAILABLE
        room.setStatus(Room.RoomStatus.AVAILABLE);
        roomRepository.save(room);

        // 从数据库中删除预定
        reservationRepository.delete(reservation);


    }

    @Override
    public Optional<Reservation> getReservationByUser(Long userId){
        return reservationRepository.findByUserId(userId);
    }
}
