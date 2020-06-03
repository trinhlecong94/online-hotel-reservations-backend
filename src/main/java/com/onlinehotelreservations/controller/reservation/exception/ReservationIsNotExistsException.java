package com.onlinehotelreservations.controller.reservation.exception;

public class ReservationIsNotExistsException extends RuntimeException {
    public ReservationIsNotExistsException(int id) {
        super("Reservation is n exist with id: " + id);
    }
}
