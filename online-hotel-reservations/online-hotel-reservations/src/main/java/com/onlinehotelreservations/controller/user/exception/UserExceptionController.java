package com.onlinehotelreservations.controller.user.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(value = UserIsExistsException.class)
    public ResponseEntity<Object> exception(UserIsExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.CONFLICT, exception));
    }

    @ExceptionHandler(value = UserIsNotExistsException.class)
    public ResponseEntity<Object> exception(UserIsNotExistsException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.BAD_GATEWAY, exception));
    }
}
