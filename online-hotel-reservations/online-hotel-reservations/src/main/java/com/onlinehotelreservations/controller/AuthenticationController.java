package com.onlinehotelreservations.controller;

import com.onlinehotelreservations.config.JwtTokenUtil;
import com.onlinehotelreservations.entity.AuthenticationRequest;
import com.onlinehotelreservations.entity.AuthenticationResponse;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.service.AuthenticationService;
import com.onlinehotelreservations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        userDetailsService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final UserEntity userEntity = userService.findByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token, userEntity.getUsername(),
                userEntity.getFirstName(), userEntity.getAccountRoles()));
    }

}
