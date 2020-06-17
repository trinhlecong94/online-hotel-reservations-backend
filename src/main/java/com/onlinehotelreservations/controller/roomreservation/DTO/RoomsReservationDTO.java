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
import java.util.List;

@Data
public class RoomsReservationDTO {

    private int id;

    private int brandId;

    private int roomTypeId;

    private Reservation reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private List<UserDTO> users;

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

        @NotNull
        private int occupancyLimit;

    }

}
