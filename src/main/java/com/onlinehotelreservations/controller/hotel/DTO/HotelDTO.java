package com.onlinehotelreservations.controller.hotel.DTO;

import com.onlinehotelreservations.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

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
    public static class Brand {
        @NotNull
        private int id;

        private String name;
    }
}
