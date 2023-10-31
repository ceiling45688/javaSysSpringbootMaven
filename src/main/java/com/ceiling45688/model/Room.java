package com.ceiling45688.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms",
        uniqueConstraints = @UniqueConstraint(columnNames = {"roomNumber", "apartment_id"}))
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber; // 房间号码, 如3

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Reservation reservation;

    public enum RoomType {
        BRONZE, SILVER, GOLD
    }

    public enum RoomStatus {
        AVAILABLE, OCCUPIED
    }

    public Room(){
        // 没想好这个constructor还要不要
    }
    public Room(String roomNumber){
        this.roomNumber = roomNumber;
    }


    // getter and setter
    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
