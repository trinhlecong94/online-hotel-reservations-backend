package com.onlinehotelreservations.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int floor;

    @NotNull
    private int occupancyLimit;

    @NotNull
    @OneToOne
    private RoomTypeEntity roomType;

    @NotNull
    @OneToOne
    private HotelEntity hotel;

    public RoomEntity() {
    }

    public RoomEntity(int id, String name, int floor, int occupancyLimit, RoomTypeEntity roomType, HotelEntity hotel) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.occupancyLimit = occupancyLimit;
        this.roomType = roomType;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getOccupancyLimit() {
        return occupancyLimit;
    }

    public void setOccupancyLimit(int occupancyLimit) {
        this.occupancyLimit = occupancyLimit;
    }

    public RoomTypeEntity getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeEntity roomType) {
        this.roomType = roomType;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

}
