package com.onlinehotelreservations.controller.roomreservation.exception;

public class RoomReservationIsExistsException extends RuntimeException {
    public RoomReservationIsExistsException(int id) {
        super("Room Reservation is exist with id: " + id);
    }
}
