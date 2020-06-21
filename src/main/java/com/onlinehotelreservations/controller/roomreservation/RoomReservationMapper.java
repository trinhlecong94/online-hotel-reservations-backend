package com.onlinehotelreservations.controller.roomreservation;

import com.onlinehotelreservations.controller.reservation.DTO.ReservationDTO;
import com.onlinehotelreservations.controller.reservation.ReservationMapper;
import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.entity.ReservationEntity;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomReservationEntity;
import com.onlinehotelreservations.service.ReservationService;
import com.onlinehotelreservations.service.RoomReservationService;
import com.onlinehotelreservations.service.RoomService;
import com.onlinehotelreservations.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class RoomReservationMapper {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomReservationService roomReservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReservationMapper reservationMapper;

    @Mapping(source = ".", target = "room", qualifiedByName = "mapToRoomEntity")
    @Mapping(source = ".", target = "reservation", qualifiedByName = "mapToReservationEntity")
    public abstract RoomReservationEntity toRoomReservationEntity(RoomReservationDTO roomReservationDTO);

    @Mapping(source = ".", target = "reservation", qualifiedByName = "mapToReservationDTO")
    public abstract RoomReservationDTO toRoomReservationDTO(RoomReservationEntity roomReservationEntity);

    public List<RoomReservationDTO> toRoomReservationDTOs(List<RoomReservationEntity> roomReservationEntities) {
        return roomReservationEntities.parallelStream().map(this::toRoomReservationDTO).collect(Collectors.toList());
    }

    public RoomEntity mapToRoomEntity(final RoomReservationDTO roomReservationDTO) {
        return this.roomService.getRoomFollowID(roomReservationDTO.getRoom().getId());
    }

    public ReservationEntity mapToReservationEntity(final RoomReservationDTO roomReservationDTO) {
        return this.reservationService.getReservationFollowId(roomReservationDTO.getReservation().getId());
    }

    public ReservationDTO mapToReservationDTO(final RoomReservationEntity roomReservationEntity){
        return this.reservationMapper.toReservationDTO(
                this.reservationService.getReservationFollowId(
                        roomReservationEntity.getReservation().getId()));
    }

}
