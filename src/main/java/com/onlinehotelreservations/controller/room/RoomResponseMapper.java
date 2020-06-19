package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomResponseDTO;
import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationDTO;
import com.onlinehotelreservations.controller.roomreservation.RoomReservationMapper;
import com.onlinehotelreservations.entity.RoomEntity;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomResponseMapper {

    @Autowired
    private RoomReservationMapper roomReservationMapper;

    @Mapping(source = ".", target = "roomReservationDTOList", qualifiedByName = "mapToRoomResponseDTOs")
    public abstract RoomResponseDTO toRoomResponseDTO(RoomEntity roomEntity);

    public List<RoomResponseDTO> toRoomResponseDTOs(List<RoomEntity> roomEntities) {
        return roomEntities.parallelStream().map(this::toRoomResponseDTO).collect(Collectors.toList());
    }

    public List<RoomReservationDTO> mapToRoomResponseDTOs(RoomEntity roomEntity) {
        List<RoomReservationDTO> roomReservationDTOS = new ArrayList<>();
        if (roomEntity.getRoomReservation() != null) {
            roomReservationDTOS = this.roomReservationMapper.toRoomReservationDTOs(roomEntity.getRoomReservation());
        }
        return roomReservationDTOS;
    }
}
