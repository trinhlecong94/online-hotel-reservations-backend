package com.onlinehotelreservations.controller.hotel.exception;

public class NameHotelIsExistException extends RuntimeException {
    public NameHotelIsExistException(String name) {
        super("Name hotel is exist with name: " + name);
    }
}
