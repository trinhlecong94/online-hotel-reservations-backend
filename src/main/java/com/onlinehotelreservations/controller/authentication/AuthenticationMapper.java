package com.onlinehotelreservations.controller.authentication;

import com.onlinehotelreservations.controller.authentication.DTO.RegisterDTO;
import com.onlinehotelreservations.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AuthenticationMapper {
    public abstract UserEntity toUserEntity(RegisterDTO registerDTO);
}
