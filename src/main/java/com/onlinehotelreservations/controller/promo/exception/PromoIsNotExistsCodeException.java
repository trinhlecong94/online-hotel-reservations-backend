package com.onlinehotelreservations.controller.promo.exception;

public class PromoIsNotExistsCodeException extends RuntimeException {
    public PromoIsNotExistsCodeException(String code) {
        super("Promo is n exist with Code: " + code);
    }
}
