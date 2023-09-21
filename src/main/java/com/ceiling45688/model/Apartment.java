package com.ceiling45688.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="apartments")
public class Apartment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键 自动

    @Column(nullable = false, unique = true)
    private String apartmentNumber; // 公寓号码，如20


    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Room> rooms;

    // getter and setter

    public Long getId() {
        return id;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
