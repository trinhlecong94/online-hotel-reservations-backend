package com.onlinehotelreservations.controller.hotel.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Data
    public static class Brand {
        @NotNull
        private int id;

        private String name;
    }
}
