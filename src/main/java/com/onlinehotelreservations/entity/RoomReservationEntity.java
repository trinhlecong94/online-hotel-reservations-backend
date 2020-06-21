package com.onlinehotelreservations.entity;

import com.onlinehotelreservations.shared.enums.RoomReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "room_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    private RoomEntity room;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private ReservationEntity reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private RoomReservationStatus status = RoomReservationStatus.PENDING;

}
