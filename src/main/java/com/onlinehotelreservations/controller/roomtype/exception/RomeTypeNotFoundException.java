package com.onlinehotelreservations.controller.roomtype.exception;

public class RomeTypeNotFoundException extends RuntimeException {
    public RomeTypeNotFoundException(int id) {
        super("RoomType id not found : " + id);
    }
}
