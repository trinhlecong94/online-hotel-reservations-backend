package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomDTO;
import com.onlinehotelreservations.controller.room.DTO.RoomStatusDTO;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.service.BrandService;
import com.onlinehotelreservations.service.RoomService;
import com.onlinehotelreservations.service.RoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    @Autowired
    private BrandService brandService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomService roomService;

    @Mapping(source = ".", target = "brand", qualifiedByName = "mapToBrandEntity")
    @Mapping(source = ".", target = "roomType", qualifiedByName = "mapToRoomType")
    public abstract RoomEntity toRoomEntity(RoomDTO roomDTO);

    public abstract RoomDTO toRoomDTO(RoomEntity roomEntity);

    public List<RoomDTO> toRoomDT0s(List<RoomEntity> roomEntities) {
        return roomEntities.parallelStream().map(this::toRoomDTO).collect(Collectors.toList());
    }

    public BrandEntity mapToBrandEntity(final RoomDTO roomDTO) {
        return this.brandService.getBrandFollowID(roomDTO.getBrand().getId());
    }

    public RoomTypeEntity mapToRoomType(final RoomDTO roomDTO) {
        return this.roomTypeService.getRoomTypeFollowId(roomDTO.getRoomType().getId());
    }

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
