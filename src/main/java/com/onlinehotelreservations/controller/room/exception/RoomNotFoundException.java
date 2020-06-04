package com.onlinehotelreservations.controller.room.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(int id) {
        super("Room id not found : " + id);
    }
}
