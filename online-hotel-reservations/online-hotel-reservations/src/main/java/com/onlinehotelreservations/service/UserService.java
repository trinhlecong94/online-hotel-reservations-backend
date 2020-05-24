package com.onlinehotelreservations.service;

import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public UserEntity findUserById(int id) {
        return userRepository.findById(id).
                orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id " + id + " is not found.");
                });
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(int id) {
        userRepository.findById(id).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id " + id + " is not found.");
        });
        userRepository.deleteById(id);
    }

    public UserEntity updateUser(UserEntity userEntity, int id) {
        return userRepository.findById(id)
                .map(x -> {
                    if (!userEntity.getAccountRoles().isEmpty()) {
                        x.setAccountRoles(userEntity.getAccountRoles());
                    }
                    if (!userEntity.getAddress().isEmpty()) {
                        x.setAddress(userEntity.getAddress());
                    }
                    if (userEntity.getBirthday() != null) {
                        x.setBirthday(userEntity.getBirthday());
                    }
                    if (!userEntity.getCity().isEmpty()) {
                        x.setCity(userEntity.getCity());
                    }
                    if (!userEntity.getFirstName().isEmpty()) {
                        x.setFirstName(userEntity.getFirstName());
                    }
                    if (!userEntity.getLastName().isEmpty()) {
                        x.setLastName(userEntity.getLastName());
                    }
                    if (!userEntity.getPhone().isEmpty()) {
                        x.setPhone(userEntity.getPhone());
                    }
                    if (userEntity.getStatus() != null) {
                        x.setStatus(userEntity.getStatus());
                    }
                    return userRepository.save(x);
                })
                .orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id " + id + " is not found.");
                });
    }
}
