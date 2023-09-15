package com.ceiling45688.model;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceRequest {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}
