package com.onlinehotelreservations.controller.authentication.exception;

public class InActiveStatusUserException extends RuntimeException {
    public InActiveStatusUserException(String username) {
        super("Status user " + username + " inActive status");
    }
}
