package com.onlinehotelreservations.controller.roomreservation.exception;

public class RoomReservationIsNotExistsException extends RuntimeException {
    public RoomReservationIsNotExistsException(int id) {
        super("Room reservation is n exist with id: " + id);
    }
}
