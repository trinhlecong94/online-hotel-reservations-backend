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
@Table(name = "reservation_detail")
public class ReservationDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @NotNull
    private ReservationEntity reservation;

    @OneToOne
    @NotNull
    private RoomReservationEntity roomReservation;

    private String description;

    private double rate;

    public ReservationDetailEntity() {
    }

    public ReservationDetailEntity(int id, ReservationEntity reservation, RoomReservationEntity roomReservation, String description, double rate) {
        this.id = id;
        this.reservation = reservation;
        this.roomReservation = roomReservation;
        this.description = description;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public RoomReservationEntity getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservationEntity roomReservation) {
        this.roomReservation = roomReservation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
