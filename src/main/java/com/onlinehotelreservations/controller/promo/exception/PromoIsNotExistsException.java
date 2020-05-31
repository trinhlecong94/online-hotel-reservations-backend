package com.onlinehotelreservations.controller.promo.exception;

public class PromoIsNotExistsException extends RuntimeException {
    public PromoIsNotExistsException(int id) {
        super("Promo is n exist with id: " + id);
    }
}
