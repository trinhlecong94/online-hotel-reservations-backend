package com.onlinehotelreservations.controller.authentication.exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationException {
    @ExceptionHandler(value = EmailLoginFailedException.class)
    public ResponseEntity<Object> exception(EmailLoginFailedException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.FORBIDDEN, exception));
    }

    @ExceptionHandler(value = PasswordLoginFailedException.class)
    public ResponseEntity<Object> exception(PasswordLoginFailedException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.FORBIDDEN, exception));
    }

    @ExceptionHandler(value = InActiveStatusUserException.class)
    public ResponseEntity<Object> exception(InActiveStatusUserException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.FORBIDDEN, exception));
    }
}
