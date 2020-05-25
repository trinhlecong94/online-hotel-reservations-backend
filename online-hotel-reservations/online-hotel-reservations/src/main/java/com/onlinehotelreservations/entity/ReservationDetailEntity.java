package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
