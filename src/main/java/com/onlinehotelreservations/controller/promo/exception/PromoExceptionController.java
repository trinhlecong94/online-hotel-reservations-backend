package com.onlinehotelreservations.controller.promo.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PromoExceptionController {
    @ExceptionHandler(value = PromoNotFoundException.class)
    public ResponseEntity<Object> exception(PromoNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = PromoIsExistsException.class)
    public ResponseEntity<Object> exception(PromoIsExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = PromoIsNotExistsException.class)
    public ResponseEntity<Object> exception(PromoIsNotExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.BAD_GATEWAY, exception));
    }
}
