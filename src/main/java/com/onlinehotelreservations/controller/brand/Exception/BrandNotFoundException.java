package com.onlinehotelreservations.controller.brand.Exception;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(int id) {
        super("Brand not found with id: " + id);
    }
}
