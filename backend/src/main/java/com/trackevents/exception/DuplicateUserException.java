package com.trackevents.exception;

public class DuplicateUserException extends TrackEventsException {
    public DuplicateUserException(String email) {
        super("A user with email " + email + " already exists.");
    }
}