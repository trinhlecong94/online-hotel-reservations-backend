package com.onlinehotelreservations.controller.promo.DTO;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.entity.ExtraEntity;
import com.onlinehotelreservations.entity.ImageEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class PromoDTO {

    private int id;

    @NotNull
    private String promoCode;

    @NotNull
    private String description;

    private double dollarDiscount;

    private double percentDiscount;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private List<PromoDTO.RoomType> roomType;

    @Data
    public static class RoomType {

        private int id;

        @NotNull
        private String name;

        @NotNull
        private double price;

        @NotNull
        private String type;

        @NotNull
        private double size;

        @NotNull
        private double capacity;

        @NotNull
        private String description;

        private Set<ImageEntity> images;

        private Set<ExtraEntity> extras;
    }
}
