package com.trackevents.controller;


import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.model.EventInfo;
import com.trackevents.model.Events;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Users;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EventsController {

    private final EventService eventService;

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<EventDto> createEvent(@RequestBody Events event) {

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PostMapping("/addUser")
    public ResponseEntity<EventDto> addUsers(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.addUsers(info));
    }

    @PostMapping("/discardUser")
    public ResponseEntity<EventDto> discardUsers(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.discardUsers(info));
    }

    @PostMapping("/event")
    public ResponseEntity<List<EventDto>> displayEvents(@RequestBody int id) {
        eventService.setExpired();
        Users target = user.getById(id);
        List<Events> listWithoutDuplicates = target.getEvents();

        return listWithoutDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @GetMapping("/events")
    public List<Events> displayEvents() {
        eventService.setExpired();
        List<Events> events = eventService.getAllEvents();

        if (events != null) {
            Collections.sort(events, (o1, o2) -> {
                if (o1.getParticipants().size() > o2.getParticipants().size()) {
                    return -1;
                } else if (o1.getParticipants().size() == o2.getParticipants().size()) {
                    return 0;
                } else {
                    return 1;
                }
            });
            while (events.size() != 5) {
                events.remove(events.size() - 1);
            }

            return events;

        } else {
            return null;
        }
    }

    @PostMapping("/month")
    public List<Integer> getMonth(@RequestBody int id) {
        AtomicInteger i1 = new AtomicInteger(0);
        AtomicInteger i2 = new AtomicInteger(0);
        AtomicInteger i3 = new AtomicInteger();
        AtomicInteger i4 = new AtomicInteger();


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH);

        Users u = userService.getById(id);

        List<Events> listWithoutDuplicates = u.getEvents();
        List<Events> events = listWithoutDuplicates.stream()
                .distinct()
                .toList();

        events.forEach((event -> {
            cal.setTime(event.getEventDate());
            int startMonth = cal.get(Calendar.MONTH);
            if (startMonth == month || startMonth == month + 1 || startMonth == month + 2 || startMonth == month + 3) {
                if (startMonth == month) {
                    i1.incrementAndGet();
                } else if (startMonth == month + 1) {
                    i2.incrementAndGet();
                } else if (startMonth == month + 2) {
                    i3.incrementAndGet();
                } else if (startMonth == month + 3) {
                    i4.incrementAndGet();
                }
            }

        }));
        List<Integer> data = new ArrayList<>();
        data.add(i1.get());
        data.add(i2.get());
        data.add(i3.get());
        data.add(i4.get());


        return data;

    }


    @PostMapping("/absent")
    public List<Users> displayAbsent(@RequestBody int id) {
        Events event = eventService.findById(id);
        List<Users> users = userService.getAllUsers();
        List<Users> absents = new ArrayList<>();

        users.forEach((users1 -> {
            if (!event.getParticipants().contains(users1)) {
                absents.add(users1);
            }
        }));

        return absents;

    }

    @PostMapping("/upcoming")
    public List<EventInfo> getUpcoming(@RequestBody int id) {
        eventService.setExpired();
        Users target = userService.getById(id);

        List<Events> all = target.getEvents();

        List<Events> upcomings = new ArrayList<>();

        all.forEach(event ->{
            if(!event.isExpired()){
                upcomings.add(event);
            }
        });

        Collections.sort(upcomings, new Comparator<Events>() {
            @Override
            public int compare(Events o1, Events o2) {
                if (o1.getEventExpired().compareTo(o2.getEventExpired()) > 0) {
                    return 1;
                } else if (o1.getEventExpired().compareTo(o2.getEventExpired()) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    List<Events> upcoming= upcomings.stream()
        .distinct()
        .toList();

    List<EventInfo> eventInfo= new ArrayList<>();

    upcoming.forEach((event)->{
        eventInfo.add(new EventInfo(event.getEventTitle(),calculateDifference(new Date(),event.getEventExpired()),event.getEventId(),event.getEventExpired()));
    });

    if(eventInfo.size()<5){
        return eventInfo;
    }
    else if(eventInfo.size()>5){
        while(eventInfo.size()!=5){
            eventInfo.remove(eventInfo.size()-1);
        }
        return eventInfo;
    }

        return eventInfo;


    }

    private long calculateDifference(Date d1,Date d2){
        long diffInMillis = Math.abs(d1.getTime() - d2.getTime());

        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

}