package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.authentication.exception.EmailLoginFailedException;
import com.onlinehotelreservations.controller.authentication.exception.InActiveStatusUserException;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.UserRepository;
import com.onlinehotelreservations.shared.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    public boolean isHandleEmail(String email) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);
        if (!userEntity.isPresent()) {
            throw new EmailLoginFailedException();
        }
        return true;
    }

    public boolean isHandleStatus(String email) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            if (userEntity.get().getStatus().equals(Status.INACTIVE)) {
                throw new InActiveStatusUserException(email);
            }
        }
        return true;
    }
}
