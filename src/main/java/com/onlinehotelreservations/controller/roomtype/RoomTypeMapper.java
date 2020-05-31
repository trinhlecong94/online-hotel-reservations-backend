package com.onlinehotelreservations.controller.roomtype;

import com.onlinehotelreservations.controller.roomtype.DTO.RoomTypeDTO;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class RoomTypeMapper {
    public abstract RoomTypeEntity toRoomTypeEntity(RoomTypeDTO roomTypeDTO);

   public abstract RoomTypeDTO toRoomTypeDTO(RoomTypeEntity roomTypeEntity);

    public List<RoomTypeDTO> toRoomTypeDTOs(List<RoomTypeEntity> roomTypeEntities) {
        return roomTypeEntities.parallelStream().map(this::toRoomTypeDTO).collect(Collectors.toList());
    }
}
