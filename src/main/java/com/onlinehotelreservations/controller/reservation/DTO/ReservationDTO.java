package com.onlinehotelreservations.controller.reservation.DTO;

import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.shared.enums.Status;
import lombok.Data;

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

    @Data
    public static class User {

        private int id;

        private String email;

        private String password;

        private Set<RoleEntity> roleEntities;

        private String firstName;

        private String lastName;

        private Status status = Status.ACTIVE;

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
