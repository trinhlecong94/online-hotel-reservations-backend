package com.onlinehotelreservations.controller.authentication;

import com.onlinehotelreservations.controller.authentication.DTO.RegisterDTO;
import com.onlinehotelreservations.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AuthenticationMapper {
    public abstract UserEntity toUserEntity(RegisterDTO registerDTO);
}
