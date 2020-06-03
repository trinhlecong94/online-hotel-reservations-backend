package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.brand.DTO.BrandDTO;
import com.onlinehotelreservations.controller.room.DTO.RoomDTO;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.service.HotelService;
import com.onlinehotelreservations.service.RoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Mapping(source = ".", target = "hotel", qualifiedByName = "mapToHotelEntity")
    @Mapping(source = ".", target = "roomType", qualifiedByName = "mapToRoomType")
    public abstract RoomEntity toRoomEntity(RoomDTO roomDTO);

    public abstract RoomDTO toRoomDTO(RoomEntity roomEntity);

    public List<RoomDTO> toRoomDT0s(List<RoomEntity> roomEntities){
        return roomEntities.parallelStream().map(this::toRoomDTO).collect(Collectors.toList());
    };

    public HotelEntity mapToHotelEntity(final RoomDTO roomDTO) {
        return this.hotelService.getHotelFollowID(roomDTO.getHotel().getId());
    }
    public RoomTypeEntity mapToRoomType(final RoomDTO roomDTO) {
        return this.roomTypeService.getRoomTypeFollowId(roomDTO.getRoomType().getId());
    }
}
