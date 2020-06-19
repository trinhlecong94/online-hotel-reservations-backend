package com.onlinehotelreservations.controller.roomreservation.DTO;

import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.shared.enums.RoomReservationStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class RoomReservationDTO {

    private int id;

    private Room room;

    private Reservation reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private Set<UserDTO> users;

    private UserDTO usersBooking;

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
