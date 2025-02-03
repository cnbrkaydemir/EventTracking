package com.trackevents.exception;

public class UserNotFoundException extends TrackEventsException {
    public UserNotFoundException(int userId) {
        super("User with ID " + userId + " not found.");
    }
}
