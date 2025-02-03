package com.trackevents.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {
        super("Unauthorized action: " + message);
    }
}
