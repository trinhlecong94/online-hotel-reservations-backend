package com.onlinehotelreservations.controller.roomreservation;

import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationDTO;
import com.onlinehotelreservations.entity.*;
import com.onlinehotelreservations.service.*;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Mapping(source = ".", target = "room", qualifiedByName = "mapToRoomEntity")
    @Mapping(source = ".", target = "reservation", qualifiedByName = "mapToReservationEntity")
    @Mapping(source = ".", target = "users", qualifiedByName = "mapToUserEntities")
    public abstract RoomReservationEntity toRoomReservationEntity(RoomReservationDTO roomReservationDTO);

    public abstract RoomReservationDTO toRoomReservationDTO(RoomReservationEntity roomReservationEntity);

    public List<RoomReservationDTO> toRoomReservationDTOs(List<RoomReservationEntity> roomReservationEntities) {
        return roomReservationEntities.parallelStream().map(this::toRoomReservationDTO).collect(Collectors.toList());
    }

    public RoomEntity mapToRoomEntity(final RoomReservationDTO roomReservationDTO) {
        return this.roomService.getRoomFollowID(roomReservationDTO.getRoom().getId());
    }

    public ReservationEntity mapToReservationEntity(final RoomReservationDTO roomReservationDTO) {
        if (roomReservationDTO.getReservation().getId() == 0) {
            ReservationEntity reservation = new ReservationEntity();
            reservation.setUser(roomReservationDTO.getUsers().get(0));
            return this.reservationService.addNewReservation(reservation);
        }
        return this.reservationService.getReservationFollowId(roomReservationDTO.getReservation().getId());
    }

    public List<UserEntity> mapToUserEntities(final RoomReservationDTO roomReservationDTO) {
        List<UserEntity> userEntity = new ArrayList<>();
        for (UserEntity user : roomReservationDTO.getUsers()) {
            userEntity.add(this.userService.getUserFollowId(user.getId()));
        }
        return userEntity;
    }
}
