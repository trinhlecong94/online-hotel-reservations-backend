package com.onlinehotelreservations.controller.roomreservation.DTO;

import com.onlinehotelreservations.controller.reservation.DTO.ReservationDTO;
import com.onlinehotelreservations.shared.enums.RoomReservationStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RoomReservationDTO {

    private int id;

    private Room room;

    private ReservationDTO reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private RoomReservationStatus status = RoomReservationStatus.PENDING;

    @Data
    public static class Room {
        private int id;

        @NotNull
        private String name;

        @NotNull
        private int floor;

    }

}
