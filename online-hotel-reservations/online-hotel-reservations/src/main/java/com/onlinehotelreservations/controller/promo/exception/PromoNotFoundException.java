package com.onlinehotelreservations.controller.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User id not found : " + id);
    }
}
