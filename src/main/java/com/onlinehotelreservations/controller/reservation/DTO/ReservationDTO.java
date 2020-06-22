package com.onlinehotelreservations.controller.reservation.DTO;

import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.entity.PromoEntity;
import com.onlinehotelreservations.shared.enums.ReservationStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class ReservationDTO {

    private int id;

    @NotNull
    private UserDTO user;

    private double totalBeforeTax;

    private double taxPercent = 10;

    private double totalAfterTax;

    private Set<PromoEntity> promos;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.UNPAID;

    @Data
    public static class Promo {

        private int id;

        private String promoCode;

        private String description;

        private double dollarDiscount;

        private double percentDiscount;

        private Date startDate;

        private Date endDate;

        //private RoomTypeEntity roomType;
    }
}
