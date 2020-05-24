package com.onlinehotelreservations.controller;

import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    Iterable<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('USER')")
    UserEntity findUserById(@PathVariable int id) throws Exception {
        return userService.findUserById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity newUser(@RequestBody UserEntity newUser) {
        return userService.saveUser(newUser);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('USER')")
    UserEntity updateUser(@RequestBody UserEntity userEntity, @PathVariable int id) {
        return userService.updateUser(userEntity, id);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUser(@PathVariable int id) {
        userService.findUserById(id);
    }
}
