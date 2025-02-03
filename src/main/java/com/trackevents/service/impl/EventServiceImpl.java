package com.trackevents.service.impl;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.dto.UserDto;
import com.trackevents.exception.EventNotFoundException;
import com.trackevents.exception.UserNotFoundException;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private  final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "event")
    public EventDto findById(int id){
        return modelMapper.map(eventRepository.findByEventId(id), EventDto.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = "all_event", allEntries = true)
    public EventDto createEvent(Events event){
        Users adminUser = userRepository.findByUserId(event.getCreated_by().getUserId())
                .orElseThrow(() -> new UserNotFoundException(event.getCreated_by().getUserId()));

        event.setCreatedBy("admin");

        event.setCreatedDate(new Date());

        this.setExpired(event);

        event.setCreated_by(adminUser);
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }


    @Override
    @Transactional
    @CacheEvict(value = "event", allEntries = true)
    public EventDto addUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId())
                .orElseThrow(() -> new EventNotFoundException(info.getEventId()));

        info.getUsers().forEach((userId) -> {
            Users participant = userRepository.findByUserId(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));

            participant.getEvents().add(event);
            event.getParticipants().add(participant);
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);

    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "user_event")
    public List<EventDto> getUserEvents(int userId){

        Users target = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Events> listWithoutDuplicates = target.getEvents();

        return listWithoutDuplicates.stream()
                .distinct()
                .toList().stream().map((ev) -> modelMapper.map(ev, EventDto.class)).toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = "user_event", allEntries = true)
    public EventDto discardUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId())
                .orElseThrow(() -> new EventNotFoundException(info.getEventId()));

        info.getUsers().forEach((userId) -> {
            Users participant = userRepository.findByUserId(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));

            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "all_event")
    public List<EventDto> getAllEvents(){

        List<Events> events = eventRepository.findAll();

        events.sort((o1, o2) -> Integer.compare(o2.getParticipants().size(), o1.getParticipants().size()));

        return events.stream().map(event -> modelMapper.map(event, EventDto.class)).toList();

    }


    @Override
    @Transactional(readOnly = true)
    public List<Integer> calculateMonthlyDistribution(int userId) {
        AtomicInteger[] monthCounts = {new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};

        // Get the current month
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);

        // Fetch user and their distinct events
        Users user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Events> events = user.getEvents().stream().distinct().toList();

        // Count events for the current month and the next three months
        events.forEach(event -> {
            cal.setTime(event.getEventDate());
            int eventMonth = cal.get(Calendar.MONTH);

            // Calculate the month difference (handle overflow with modulo 12)
            int monthDifference = (eventMonth - currentMonth + 12) % 12;
            if (monthDifference <= 3) {
                monthCounts[monthDifference].incrementAndGet();
            }
        });

        // Convert counts to a list of integers
        return Arrays.stream(monthCounts)
                .map(AtomicInteger::get)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "event_user")
    public List<UserDto> findAbsent(int eventId) {
        Events event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        List<Users> users = userRepository.findAll();

        List<Users> absents = new ArrayList<>();

        users.forEach((users1 -> {
            if (!event.getParticipants().contains(users1)) {
                absents.add(users1);
            }
        }));

        return absents.stream().map((user) -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "event")
    public List<EventDto> upcomingEvents(int userId) {

        Users target = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Events> userEvents = target.getEvents();


        userEvents.forEach(event ->{
            if(event.isExpired()){
                userEvents.remove(event);
            }
        });

        userEvents.sort(Comparator.comparing(Events::getEventExpired));


        return userEvents
                .stream()
                .distinct()
                .map((event) -> modelMapper.map(event, EventDto.class))
                .toList();

    }

    @Override
    public void setExpired(Events event){
        long millis=System.currentTimeMillis();
        Date date= new Date(millis);
        event.setExpired(event.getEventExpired().compareTo(date) < 0);
    }


}
