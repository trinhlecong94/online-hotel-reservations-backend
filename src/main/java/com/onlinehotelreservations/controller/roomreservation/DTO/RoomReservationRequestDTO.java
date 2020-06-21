package com.onlinehotelreservations.controller.roomreservation.DTO;

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
public class RoomReservationRequestDTO {

    private int id;

    private int brandId;

    private int roomTypeId;

    private Reservation reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @Temporal(TemporalType.DATE)
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
    public static class Reservation {

        private int id;

    }

    @Data
    public static class Room {
        private int id;

        @NotNull
        private String name;

        @NotNull
        private int floor;

    }

}
