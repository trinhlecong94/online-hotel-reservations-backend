package com.onlinehotelreservations.controller.authentication;

import com.onlinehotelreservations.config.TokenProvider;
import com.onlinehotelreservations.controller.authentication.DTO.AuthTokenDTO;
import com.onlinehotelreservations.controller.authentication.DTO.LoginDTO;
import com.onlinehotelreservations.controller.authentication.DTO.RegisterDTO;
import com.onlinehotelreservations.controller.authentication.exception.PasswordLoginFailedException;
import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.service.AuthService;
import com.onlinehotelreservations.service.UserService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final AuthenticationMapper authenticationMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody @Validated LoginDTO login) {
        if (this.authenticationService.isHandleEmail(login.getEmail()) &&
                this.authenticationService.isHandleStatus(login.getEmail())) {
            try {
                final Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                login.getEmail(),
                                login.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                final String token = jwtTokenUtil.generateToken(authentication);
                return ResponseEntity.ok(new AuthTokenDTO(this.userMapper.toUserDTO(this.userService.getUserByEmail(login.getEmail())), token));
            } catch (Exception e) {
                throw new PasswordLoginFailedException();
            }
        }
        throw new UsernameNotFoundException("Login failed");
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ApiData<UserDTO> register(@RequestBody @Validated RegisterDTO registerDTO) {
        return new ApiData<>(this.userMapper.toUserDTO(this.userService.addNewUser(
                this.authenticationMapper.toUserEntity(registerDTO)
        )));
    }

}
