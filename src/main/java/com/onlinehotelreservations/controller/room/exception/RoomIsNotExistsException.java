package com.onlinehotelreservations.controller.room.exception;

public class RoomIsNotExistsException extends RuntimeException {
    public RoomIsNotExistsException(int id) {
        super("Room is n exist with id: " + id);
    }
}
