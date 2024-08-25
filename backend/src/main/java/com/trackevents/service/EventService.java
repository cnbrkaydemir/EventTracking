package com.trackevents.service;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.model.Events;

import java.util.List;

public interface EventService {

    public void setExpired();

    public EventDto saveEvent(Events event);

    public EventDto createEvent(Events event);

    public EventDto addUsers(ParticipationDto info);

    public EventDto discardUsers(ParticipationDto info);

    public List<EventDto> getAllEvents();

    public EventDto findById(int id);
}
