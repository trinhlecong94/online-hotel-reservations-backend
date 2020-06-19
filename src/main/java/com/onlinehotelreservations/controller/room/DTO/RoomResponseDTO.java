package com.onlinehotelreservations.controller.room.DTO;

import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class RoomResponseDTO {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private int floor;

    @NotNull
    private RoomType roomType;

    @NotNull
    private Brand brand;

    private List<RoomReservationDTO> roomReservationDTOList;

    @Data
    public static class Brand {

        private int id;

        @Column(nullable = false, length = 45, unique = true)
        @NotNull
        private String name;

        private String imgLink;

        private String address;

        private  int floor;

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
