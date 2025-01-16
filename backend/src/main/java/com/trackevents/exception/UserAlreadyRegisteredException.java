package com.trackevents.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(int userId, int eventId) {
        super("User with ID " + userId + " is already registered for event with ID " + eventId + ".");
    }
}
