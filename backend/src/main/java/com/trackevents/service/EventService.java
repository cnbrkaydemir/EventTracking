package com.trackevents.service;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;

import java.util.List;

public interface EventService {

    public void setExpired();

    public List<EventDto> getUserEvents(int userId);

    public List<Integer> calculateMonth(int userId);

    public EventDto saveEvent(Events event);

    public EventDto createEvent(Events event);

    public EventDto addUsers(ParticipationDto info);

    public EventDto discardUsers(ParticipationDto info);

    public List<EventDto> getAllEvents();

    public EventDto findById(int id);

    public List<UserDto>findAbsent(int eventId);

    public List<EventDto> upcomingEvents(int userId);
}
