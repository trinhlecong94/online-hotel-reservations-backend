package com.onlinehotelreservations.controller.brand.Exception;

import com.onlinehotelreservations.controller.user.exception.UserNotFoundException;
import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BrandExceptionController {
    @ExceptionHandler(value = BrandNotFoundException.class)
    public ResponseEntity<Object> exception(BrandNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }
}
