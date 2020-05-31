package com.onlinehotelreservations.controller.brand.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BrandDTO {
    int id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    private String imgLink;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private Hotel hotel;

    @Data
    public static class Hotel {
        @NotNull
        private int id;

        private String name;
    }
}
