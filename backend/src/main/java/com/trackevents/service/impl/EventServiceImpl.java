package com.trackevents.service.impl;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

private final EventRepository eventRepository;

private final UserRepository userRepository;

private  final ModelMapper modelMapper;

    @Override
    public void setExpired(){
        List<Events> events=eventRepository.findAll();
        long millis=System.currentTimeMillis();
        Date date= new Date(millis);

        events.forEach((event)->{
            event.setExpired(event.getEventExpired().compareTo(date) < 0);
        });

    }

    @Override
    public EventDto findById(int id){
        return modelMapper.map(eventRepository.findByEventId(id), EventDto.class);
    }

    @Override
    public EventDto createEvent(Events event){
        Users adminUser = userRepository.findByUserEmail(event.getCreated_by().getUserEmail()).get(0);
        event.setCreated_by(adminUser);
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);
        eventRepository.save(event);

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    public EventDto saveEvent(Events event){
        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    public EventDto addUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId()).get();

        info.getUsers().forEach((userDto) -> {
            Users participant = userRepository.findByUserId(userDto.getUserId());
            participant.getEvents().add(event);
            event.getParticipants().add(participant);
            List<Users> u = event.getParticipants();
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);

    }

    @Override
    public List<EventDto> getUserEvents(int userId){
        this.setExpired();
        Users target = userRepository.findByUserId(userId);
        List<Events> listWithoutDuplicates = target.getEvents();

        return listWithoutDuplicates.stream()
                .distinct()
                .toList().stream().map((ev) -> modelMapper.map(ev, EventDto.class)).toList();
    }

    @Override
    public EventDto discardUsers(ParticipationDto info){
        Events event = eventRepository.findById(info.getEventId()).get();

        info.getUsers().forEach((userDto) -> {
            Users participant = userRepository.findByUserId(userDto.getUserId());
            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
        });

        return modelMapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    public List<EventDto> getAllEvents(){
       this.setExpired();
       List<Events> events = eventRepository.findAll();

       events.sort((o1, o2) -> {
           return Integer.compare(o2.getParticipants().size(), o1.getParticipants().size());
       });

       return events.stream().map(event -> modelMapper.map(event, EventDto.class)).toList();

   }


    @Override
    public List<Integer> calculateMonth(int userId) {
        AtomicInteger[] monthCounts = {new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0), new AtomicInteger(0)};

        // Get the current month
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);

        // Fetch user and their distinct events
        Users user = userRepository.findByUserId(userId);
        List<Events> events = user.getEvents().stream().distinct().toList();

        // Count events for the current month and the next three months
        events.forEach(event -> {
            cal.setTime(event.getEventDate());
            int eventMonth = cal.get(Calendar.MONTH);

            // Calculate the month difference (handle overflow with modulo 12)
            int monthDifference = (eventMonth - currentMonth + 12) % 12;
            if (monthDifference >= 0 && monthDifference <= 3) {
                monthCounts[monthDifference].incrementAndGet();
            }
        });

        // Convert counts to a list of integers
        return Arrays.stream(monthCounts)
                .map(AtomicInteger::get)
                .toList();
    }


    @Override
    public List<UserDto> findAbsent(int eventId) {
        Events event = eventRepository.findById(eventId).get();
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
    public List<EventDto> upcomingEvents(int userId) {

        this.setExpired();
        Users target = userRepository.findByUserId(userId);

        List<Events> userEvents = target.getEvents();


        userEvents.forEach(event ->{
            if(!event.isExpired()){
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

}
