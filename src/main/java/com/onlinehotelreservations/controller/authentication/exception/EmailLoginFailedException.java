package com.onlinehotelreservations.controller.authentication.exception;

public class EmailLoginFailedException extends RuntimeException {
    public EmailLoginFailedException() {
        super("Email login failed");
    }
}
