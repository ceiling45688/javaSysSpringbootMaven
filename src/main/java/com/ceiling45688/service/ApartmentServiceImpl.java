package com.ceiling45688.service;

import com.ceiling45688.model.Apartment;
import com.ceiling45688.model.Room;
import com.ceiling45688.repository.ApartmentRepository;
import com.ceiling45688.repository.ReservationRepository;
import com.ceiling45688.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationService reservationService;


    @Override
    public List<String> listAvailableApartments(){
        // 获取所有公寓。
        List<Apartment> allApartments = apartmentRepository.findAll();

        // 一个apart被视为可用的条件是它至少有一个房间的状态为available
        List<String> availableApartments = allApartments.stream()
                .filter(apartment -> apartment.getRooms().stream()
                        .anyMatch(room -> room.getStatus() == Room.RoomStatus.AVAILABLE))
                .map(Apartment::getApartmentNumber).collect(Collectors.toList());


        return availableApartments;
    }

    @Override
    public Apartment reserveApartment(Long userId, String apartmentNumber, String roomNumber,
                                      Date startDate, Date endDate){
        //从前端接收用户选择的公寓和房间信息。
        //检查该房间是否仍然可用。
        //如果房间可用，创建一个新的预定。
        //修改房间的状态为OCCUPIED。
        //更新数据库。

        // 1.从数据库中获取用户选择的公寓和房间
        Apartment selectedApartment = apartmentRepository.findByApartmentNumber(apartmentNumber)
                .orElseThrow(() -> new IllegalArgumentException("Apartment not found"));
        Room seletedRoom = roomRepository.findByRoomNumberAndAndApartment(roomNumber,selectedApartment)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        // 2. room available ?
        if(seletedRoom.getStatus() != Room.RoomStatus.AVAILABLE){
            throw new IllegalStateException("Room is already occupied!");
        }

        // 3.4. create a new reservation & change status
         //待会直接用
        reservationService.createReservation(userId,selectedApartment,seletedRoom,
                startDate, endDate);

        return selectedApartment;
    }
}
