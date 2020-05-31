package com.onlinehotelreservations.controller.hotel.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(int id) {
        super("Hotel is not found with id: " + id);
    }
}
