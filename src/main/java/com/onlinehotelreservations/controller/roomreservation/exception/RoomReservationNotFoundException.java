package com.onlinehotelreservations.controller.roomreservation.exception;

public class RoomReservationNotFoundException extends RuntimeException {
    public RoomReservationNotFoundException(int id) {
        super("Room reservation id not found : " + id);
    }
}
