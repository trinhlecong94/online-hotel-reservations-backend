package com.onlinehotelreservations.controller.hotel.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HotelExceptionController {
    @ExceptionHandler(value = HotelNotFoundException.class)
    public ResponseEntity<Object> exception(HotelNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = NameHotelIsExistException.class)
    public ResponseEntity<Object> exception(NameHotelIsExistException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }
}
