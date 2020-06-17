package com.onlinehotelreservations.controller.reservation.DTO;

import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.shared.enums.ReservationStatus;
import com.onlinehotelreservations.shared.enums.UserStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ReservationDTO {

    private int id;

    @NotNull
    private User user;

    private double totalBeforeTax;

    private double tax;

    private List<Promo> promos;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.UNPAID;

    @Data
    public static class User {

        private int id;

        private String email;

        private String password;

        private Set<RoleEntity> roleEntities;

        private String firstName;

        private String lastName;

        private UserStatus status = UserStatus.ACTIVE;

        private Date birthday;

        private String phone;
    }

    @Data
    public static class Promo {

        private int id;

        private String promoCode;

        private String description;

        private double dollarDiscount;

        private double percentDiscount;

        private Date startDate;

        private Date endDate;

        private RoomTypeEntity roomType;
    }
}
