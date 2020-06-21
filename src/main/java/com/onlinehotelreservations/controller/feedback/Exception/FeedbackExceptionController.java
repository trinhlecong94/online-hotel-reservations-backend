package com.onlinehotelreservations.controller.feedback.Exception;

import com.onlinehotelreservations.shared.Response;
import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeedbackExceptionController {
    @ExceptionHandler(value = FeedbackNotFoundException.class)
    public ResponseEntity<Object> exception(FeedbackNotFoundException exception) {
        return Response.buildResponseError(new ApiError(HttpStatus.NOT_FOUND, exception));
    }
}
