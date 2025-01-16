package com.trackevents.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(int eventId)
    {
        super("Event with ID " + eventId + " not found.");
    }
}
