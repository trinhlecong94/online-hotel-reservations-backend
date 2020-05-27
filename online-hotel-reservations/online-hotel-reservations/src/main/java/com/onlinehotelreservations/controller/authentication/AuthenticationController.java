package com.onlinehotelreservations.controller.authentication;

import com.onlinehotelreservations.config.TokenProvider;
import com.onlinehotelreservations.controller.authentication.DTO.AuthTokenDTO;
import com.onlinehotelreservations.controller.authentication.DTO.LoginDTO;
import com.onlinehotelreservations.controller.authentication.DTO.RegisterDTO;
import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.service.UserService;
import com.onlinehotelreservations.shared.model.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginDTO login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthTokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ApiData<UserDTO> register(@RequestBody @Validated RegisterDTO registerDTO) {
        return new ApiData<>(this.userMapper.toUserDTO(this.userService.addNewUser(
                this.authenticationMapper.toUserEntity(registerDTO)
        )));
    }

}
