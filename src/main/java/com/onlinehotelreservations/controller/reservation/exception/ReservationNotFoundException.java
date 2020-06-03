package com.onlinehotelreservations.controller.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(int id) {
        super("Reservation id not found : " + id);
    }
}
