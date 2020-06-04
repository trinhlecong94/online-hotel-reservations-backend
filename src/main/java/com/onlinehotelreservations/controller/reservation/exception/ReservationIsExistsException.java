package com.onlinehotelreservations.controller.reservation.exception;

public class ReservationIsExistsException extends RuntimeException {
    public ReservationIsExistsException(int id) {
        super("Reservation is exist with id: " + id);
    }
}
