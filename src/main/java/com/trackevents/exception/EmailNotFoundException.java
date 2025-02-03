package com.trackevents.exception;

public class EmailNotFoundException extends TrackEventsException {
    public EmailNotFoundException(String email)
    {
        super("Email" + email + " not found");
    }
}
