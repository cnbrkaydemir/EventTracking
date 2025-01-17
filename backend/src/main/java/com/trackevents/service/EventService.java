package com.trackevents.service;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;

import java.util.List;

public interface EventService {

    List<EventDto> getUserEvents(int userId);

    List<Integer> calculateMonthlyDistribution(int userId);

    EventDto createEvent(Events event);

    EventDto addUsers(ParticipationDto info);

    EventDto discardUsers(ParticipationDto info);

    List<EventDto> getAllEvents();

    EventDto findById(int id);

    List<UserDto>findAbsent(int eventId);

    List<EventDto> upcomingEvents(int userId);

    void setExpired(Events event);
}
