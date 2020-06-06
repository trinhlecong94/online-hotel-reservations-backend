package com.onlinehotelreservations.controller.roomreservation.DTO;

import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.shared.enums.Status;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class RoomReservationDTO {

    private int id;

    @NotNull
    private Room room;

    private Reservation reservation;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private List<UserEntity> users;

    public static class User {
        private int id;

        @NotBlank(message = "Username can't be blank")
        @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Email not match")
        private String email;

        private Set<RoleEntity> roleEntities;

        @NotBlank(message = "Fist name is required")
        @NotNull
        private String firstName;

        @NotBlank(message = "Last name is required")
        @NotNull
        private String lastName;

        @Enumerated(EnumType.STRING)
        private Status status = Status.ACTIVE;

        @DateTimeFormat(pattern = "yyyy-mm-dd")
        private Date birthday;

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "(0)+([0-9]{9})\\b", message = "Phone not in correct format")
        private String phone;
    }

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
