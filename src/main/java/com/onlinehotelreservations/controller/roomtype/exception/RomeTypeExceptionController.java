package com.onlinehotelreservations.controller.roomtype.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RomeTypeExceptionController {
    @ExceptionHandler(value = RomeTypeNotFoundException.class)
    public ResponseEntity<Object> exception(RomeTypeNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = RomeTypeIsExistsException.class)
    public ResponseEntity<Object> exception(RomeTypeIsExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = RomeTypeIsNotExistsException.class)
    public ResponseEntity<Object> exception(RomeTypeIsNotExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.BAD_GATEWAY, exception));
    }
}
