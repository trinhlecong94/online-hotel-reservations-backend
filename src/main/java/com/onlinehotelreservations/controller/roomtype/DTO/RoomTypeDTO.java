package com.onlinehotelreservations.controller.roomtype.DTO;

import com.onlinehotelreservations.entity.ExtraEntity;
import com.onlinehotelreservations.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeDTO {

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
