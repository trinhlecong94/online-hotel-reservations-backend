package com.onlinehotelreservations.controller.roomreservation.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoomReservationExceptionController {
    @ExceptionHandler(value = RoomReservationNotFoundException.class)
    public ResponseEntity<Object> exception(RoomReservationNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = RoomReservationIsExistsException.class)
    public ResponseEntity<Object> exception(RoomReservationIsExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> exception(ConflictException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = RoomReservationIsNotExistsException.class)
    public ResponseEntity<Object> exception(RoomReservationIsNotExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.BAD_GATEWAY, exception));
    }
}
