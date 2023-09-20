package com.ceiling45688.model;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceRequest {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // serviceRequestId

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Status status;


    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    private enum Status{
        PENDING, IN_PROGRESS, COMPLETED
    }

    // constructor


    // 状态转换方法
    public void initial() {
        if (this.status != null) {
            throw new IllegalStateException("Service request is already initialized.");
        }
        this.status = Status.PENDING;
    }

    public void start() {
        if (this.status != Status.PENDING) {
            throw new IllegalStateException("Cannot start a service request that is not in PENDING status.");
        }
        this.status = Status.IN_PROGRESS;
    }

    public void end() {
        if (this.status != Status.IN_PROGRESS) {
            throw new IllegalStateException("Cannot end a service request that is not in IN_PROGRESS status.");
        }
        this.status = Status.COMPLETED;
    }

    // getter and setter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getFullRoomNumber() {
        return room.getApartment().getApartmentNumber() + "." + room.getRoomNumber();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
