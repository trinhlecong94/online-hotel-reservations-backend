package com.onlinehotelreservations.controller.hotel.DTO;

import com.onlinehotelreservations.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {
    private int id;

    @NotNull
    @NotBlank
    private String name;

    private List<Brand> brands;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Set<ImageEntity> images;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Brand {
        @NotNull
        private int id;

        private String name;

        private int floor;

        private String address;

        private String imgLink;

        private String desciption;
    }
}
