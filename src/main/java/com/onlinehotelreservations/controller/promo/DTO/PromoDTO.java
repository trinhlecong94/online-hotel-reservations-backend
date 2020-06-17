package com.onlinehotelreservations.controller.promo.DTO;

import com.onlinehotelreservations.entity.ExtraEntity;
import com.onlinehotelreservations.entity.ImageEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class PromoDTO {

    private int id;

    @NotNull
    private String promoCode;

    @NotNull
    private String description;

    private double dollarDiscount;

    private double percentDiscount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @NotNull
    private RoomType roomType;

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

        @NotNull
        private String thumbnail;

    }
}
