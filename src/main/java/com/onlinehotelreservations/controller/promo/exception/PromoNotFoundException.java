package com.onlinehotelreservations.controller.promo.exception;

public class PromoNotFoundException extends RuntimeException {
    public PromoNotFoundException(int id) {
        super("Promo id not found : " + id);
    }
}
