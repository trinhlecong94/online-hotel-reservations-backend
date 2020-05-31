package com.onlinehotelreservations.controller.brand.Exception;

public class NameBrandIsExistException extends RuntimeException {
    public NameBrandIsExistException(String name) {
        super("Name brand is exist with name: " + name);
    }
}
