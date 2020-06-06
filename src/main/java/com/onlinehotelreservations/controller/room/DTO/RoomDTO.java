package com.onlinehotelreservations.controller.room.DTO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
public class RoomDTO {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private int floor;

    @NotNull
    private int occupancyLimit;

    @NotNull
    private RoomType roomType;

    @NotNull
    private Hotel hotel;

    @Data
    public static class Hotel {

        private int id;

        private String name;
    }

    @Data
    public static class RoomType {

        private int id;

        private String name;

        private double price;

        private String type;

        private double size;

        private double capacity;

        @Lob
        private String description;

        private String thumbnail;
    }
}
