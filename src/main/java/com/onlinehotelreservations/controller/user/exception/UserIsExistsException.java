package com.onlinehotelreservations.controller.user.exception;

public class UserIsExistsException extends RuntimeException {
    public UserIsExistsException(int id) {
        super("User is exist with id: " + id);
    }
}
