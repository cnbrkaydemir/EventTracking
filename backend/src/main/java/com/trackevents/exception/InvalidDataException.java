package com.trackevents.exception;

public class InvalidDataException extends TrackEventsException {
    public InvalidDataException(String message) {
        super("Invalid input data: " + message);
    }
}