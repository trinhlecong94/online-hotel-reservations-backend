package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.user.exception.UserIsExistsException;
import com.onlinehotelreservations.controller.user.exception.UserNotFoundException;
import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.RoleRepository;
import com.onlinehotelreservations.repository.UserRepository;
import com.onlinehotelreservations.shared.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public List<UserEntity> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserEntity getUserFollowId(int id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id));
        return userEntity;
    }

    public UserEntity addNewUser(UserEntity userEntity) {

        Optional<UserEntity> userEntityFromDataBase = this.userRepository.findByUserName(userEntity.getUserName());

        if (userEntityFromDataBase.isPresent()) {
            throw new UserIsExistsException(userEntityFromDataBase.get().getId());
        }

        if (userEntity.getRoleEntities() == null || userEntity.getRoleEntities().isEmpty()) {
            Set<RoleEntity> roleEntities = new HashSet<>(Arrays.asList(this.roleRepository.findByName(Role.ROLE_USER.toString())));
            userEntity.setRoleEntities(roleEntities);
        }
        return this.userRepository.save(userEntity);
    }
}
