package com.onlinehotelreservations.controller.roomtype.exception;

public class RomeTypeIsNotExistsException extends RuntimeException {
    public RomeTypeIsNotExistsException(int id) {
        super("RoomType is not exist with id: " + id);
    }
}
