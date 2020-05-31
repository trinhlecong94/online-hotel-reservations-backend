package com.onlinehotelreservations.controller.promo.exception;

public class PromoIsExistsException extends RuntimeException {
    public PromoIsExistsException(int id) {
        super("Promo is exist with id: " + id);
    }
}
