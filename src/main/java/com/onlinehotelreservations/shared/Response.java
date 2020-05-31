package com.onlinehotelreservations.shared;

import com.onlinehotelreservations.shared.model.ApiError;
import org.springframework.http.ResponseEntity;

public class Response {
    public static ResponseEntity<Object> buildResponseError(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
