package com.onlinehotelreservations.controller.reservation;

import com.onlinehotelreservations.controller.reservation.DTO.ReservationDTO;
import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.entity.ReservationEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.service.ReservationService;
import com.onlinehotelreservations.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class ReservationMapper {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Mapping(source = ".", target = "user", qualifiedByName = "mapToUserEntity")
    public abstract ReservationEntity toReservationEntity(ReservationDTO reservationDTO);

    @Mapping(source = ".", target = "user", qualifiedByName = "mapToUserDTO")
    public abstract ReservationDTO toReservationDTO(ReservationEntity reservationEntity);

    public List<ReservationDTO> toReservationDTOs(List<ReservationEntity> reservationEntities) {
        return reservationEntities.parallelStream().map(this::toReservationDTO).collect(Collectors.toList());
    }

    public UserDTO mapToUserDTO(final ReservationEntity reservationEntity) {
        return this.userMapper.toUserDTO(this.userService.getUserFollowId(reservationEntity.getUser().getId()));
    }

    public UserEntity mapToUserEntity(final ReservationDTO reservationDTO) {
        return this.userService.getUserFollowId(reservationDTO.getUser().getId());
    }
}
