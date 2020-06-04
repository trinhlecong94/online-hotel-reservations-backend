package com.onlinehotelreservations.controller.reservation.DTO;

import com.onlinehotelreservations.entity.PromoEntity;
import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.shared.enums.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/reservations")
public class ReservationDTO {

    private int id;

    @NotNull
    private User user;

    private double totalBeforeTax;

    private double tax;

    private List<Promo> promos;

    @Data
    public static class User{

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
    public static class Promo{

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
