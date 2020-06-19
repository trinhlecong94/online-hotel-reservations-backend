package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomStatusDTO;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomStatusMapper {

    @Autowired
    private RoomService roomService;

    public List<RoomStatusDTO> RoomStatusDTOs(Date startDate, Date endDate, int brandId) {
        List<RoomStatusDTO> roomStatusDTOS = new ArrayList<>();
        List<RoomEntity> roomEntities = this.roomService.getAllRoomByBrand(brandId);

        Set<RoomTypeEntity> allRoomTypeInBrand = new HashSet<>();
        for (RoomEntity roomEntity : roomEntities) {
            allRoomTypeInBrand.add(roomEntity.getRoomType());
        }

        for (RoomTypeEntity roomType : allRoomTypeInBrand) {
            List<RoomEntity> rooms = new ArrayList<>();
            for (RoomEntity roomEntity : roomEntities) {
                if (roomType.getId() == roomEntity.getRoomType().getId()) {
                    rooms.add(roomEntity);
                }
            }
            RoomStatusDTO roomStatusDTO = new RoomStatusDTO();
            roomStatusDTO.setTotalRoom(rooms.size());
            roomStatusDTO.setRoomType(roomType);
            roomStatusDTO.setTotalRoomAvailable(
                    this.roomService.getAllRoomAvailableByBandIdAndRoomTypeId(startDate, endDate, brandId, roomType.getId())
                            .size()
            );
            roomStatusDTOS.add(roomStatusDTO);
        }
        return roomStatusDTOS;
    }
}
