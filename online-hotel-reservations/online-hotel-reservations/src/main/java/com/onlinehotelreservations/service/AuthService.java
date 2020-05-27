package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.authentication.DTO.LoginDTO;
import com.onlinehotelreservations.controller.authentication.exception.EmailLoginFailedException;
import com.onlinehotelreservations.controller.authentication.exception.PasswordLoginFailedException;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    public boolean isLoginSuccess(LoginDTO loginDTO) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(loginDTO.getEmail());
        if (!userEntity.isPresent()) {
            throw new EmailLoginFailedException();
        }
        if (!new BCryptPasswordEncoder().encode(loginDTO.getPassword()).equals(userEntity.get().getPassword())) {
            throw new PasswordLoginFailedException();
        }
        return true;
    }
}
