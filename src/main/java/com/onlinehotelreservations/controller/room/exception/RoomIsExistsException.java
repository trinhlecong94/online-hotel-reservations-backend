package com.onlinehotelreservations.controller.room.exception;

public class RoomIsExistsException extends RuntimeException {
    public RoomIsExistsException(int id) {
        super("Room is exist with id: " + id);
    }
}
