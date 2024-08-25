package com.trackevents.service;

import com.trackevents.model.Events;

import java.util.List;

public interface EventService {

    public void setExpired();

    public void saveEvent(Events event);

    public void addUsers(int eventId,int userId);

    public void discardUsers(int eventId,int userId);

    public List<Events> getAllEvents();

    public Events findById(int id);
}
