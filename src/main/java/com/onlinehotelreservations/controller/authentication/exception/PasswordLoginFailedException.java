package com.onlinehotelreservations.controller.authentication.exception;

public class PasswordLoginFailedException extends RuntimeException {
    public PasswordLoginFailedException() {
        super("Password login failed");
    }
}
