package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.user.exception.UserIsExistsException;
import com.onlinehotelreservations.controller.user.exception.UserIsNotExistsException;
import com.onlinehotelreservations.controller.user.exception.UserNotFoundException;
import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.RoleRepository;
import com.onlinehotelreservations.repository.UserRepository;
import com.onlinehotelreservations.shared.enums.Role;
import com.onlinehotelreservations.shared.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public UserEntity getUserByEmail(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(0));
        return userEntity;
    }

    public UserEntity addNewUser(UserEntity userEntity) {

        Optional<UserEntity> userEntityFromDataBase = this.userRepository.findByEmail(userEntity.getEmail());

        if (userEntityFromDataBase.isPresent()) {
            throw new UserIsExistsException(userEntityFromDataBase.get().getId());
        }

        if (userEntity.getRoleEntities() == null || userEntity.getRoleEntities().isEmpty()) {
            Set<RoleEntity> roleEntities = new HashSet<>(Arrays.asList(this.roleRepository.findByName(Role.ROLE_USER.toString())));
            userEntity.setRoleEntities(roleEntities);
        }

        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        return this.userRepository.save(userEntity);
    }

    public UserEntity editUser(UserEntity user) {
        Optional<UserEntity> userGetFromDatabase = this.userRepository.findByEmail(user.getEmail());

        if (!userGetFromDatabase.isPresent()) {
            throw new UserIsNotExistsException(user.getId());
        }

        user = this.holdValueDefault(user, userGetFromDatabase.get());

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        this.userRepository.save(user);
        return this.userRepository.findById(user.getId()).get();
    }

    public void deleteUserFollowId(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserIsNotExistsException(id);
        }
        this.userRepository.deleteById(id);
    }

    public UserEntity reverseStatusUserFollowId(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserIsNotExistsException(id);
        }
        UserEntity userEntity = this.userRepository.findById(id).get();
        userEntity.setStatus(userEntity.getStatus() == UserStatus.ACTIVE ? UserStatus.INACTIVE : UserStatus.ACTIVE);
        this.userRepository.save(userEntity);

        return userEntity;
    }

    public List<UserEntity> searchUsers(String keySearch) {
        List<UserEntity> userEntities = this.userRepository.findUsersByKeyword(keySearch);
        return userEntities;
    }

    public UserEntity upToAdminFollowId(int id) {
        if (!this.userRepository.existsById(id)) {
            throw new UserIsNotExistsException(id);
        }
        UserEntity userEntity = this.userRepository.findById(id).get();

        RoleEntity roleEntity = this.roleRepository.findByName(Role.ROLE_ADMIN.toString());
        userEntity.setRoleEntities(new HashSet<>(Arrays.asList(roleEntity)));

        this.userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity holdValueDefault(UserEntity userChange, UserEntity user) {
        userChange.setRoleEntities(user.getRoleEntities());
        userChange.setId(user.getId());
        return userChange;
    }
}
