package com.onlinehotelreservations.controller.room.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoomExceptionController {
    @ExceptionHandler(value = RoomNotFoundException.class)
    public ResponseEntity<Object> exception(RoomNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = RoomIsExistsException.class)
    public ResponseEntity<Object> exception(RoomIsExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = RoomIsNotExistsException.class)
    public ResponseEntity<Object> exception(RoomIsNotExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.BAD_GATEWAY, exception));
    }
}
