package com.onlinehotelreservations.controller.user;

import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
    public abstract UserEntity toUserEntity(UserDTO userDTO);

    public abstract UserDTO toUserDTO(UserEntity userEntity);

    public List<UserDTO> toUserDTOs(List<UserEntity> userEntities) {
        return userEntities.parallelStream().map(this::toUserDTO).collect(Collectors.toList());
    }
}
