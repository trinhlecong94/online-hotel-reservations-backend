package com.onlinehotelreservations.controller.feedback.Exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(int id) {
        super("Brand not found with id: " + id);
    }
}
