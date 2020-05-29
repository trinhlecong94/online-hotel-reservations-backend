package com.onlinehotelreservations.controller.roomtype.exception;

public class RomeTypeIsExistsException extends RuntimeException {
    public RomeTypeIsExistsException(int id) {
        super("RoomType is exist with id: " + id);
    }
}
