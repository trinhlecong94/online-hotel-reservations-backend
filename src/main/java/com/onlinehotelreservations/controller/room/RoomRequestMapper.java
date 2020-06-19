package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomRequestDTO;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.service.BrandService;
import com.onlinehotelreservations.service.RoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomRequestMapper {

    @Autowired
    private BrandService brandService;

    @Autowired
    private RoomTypeService roomTypeService;


    @Mapping(source = ".", target = "brand", qualifiedByName = "mapToBrandEntity")
    @Mapping(source = ".", target = "roomType", qualifiedByName = "mapToRoomType")
    public abstract RoomEntity toRoomEntity(RoomRequestDTO roomDTO);

    public BrandEntity mapToBrandEntity(final RoomRequestDTO roomDTO) {
        return this.brandService.getBrandFollowID(roomDTO.getBrand().getId());
    }

    public RoomTypeEntity mapToRoomType(final RoomRequestDTO roomDTO) {
        return this.roomTypeService.getRoomTypeFollowId(roomDTO.getRoomType().getId());
    }

}
