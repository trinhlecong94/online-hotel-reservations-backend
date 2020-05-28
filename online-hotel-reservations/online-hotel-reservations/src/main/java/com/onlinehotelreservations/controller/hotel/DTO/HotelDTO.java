package com.onlinehotelreservations.controller.hotel.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private Set<Brand> brands;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Brand {
        @NotNull
        private int id;

        @NotNull
        @NotBlank
        private String name;
    }
}
