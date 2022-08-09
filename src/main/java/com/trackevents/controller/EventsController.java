package com.trackevents.controller;


import com.trackevents.model.Events;
import com.trackevents.model.UserDto;
import com.trackevents.model.Users;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
public class EventsController {

    private final EventService eventService;

    private final UserService usv;

    @Autowired
    public EventsController(EventService evs, UserService usv) {
        this.eventService = evs;
        this.usv = usv;
    }

    @PostMapping("/createEvent")
    public Events createEvent(@RequestBody Events event) {
        Users adminUser = usv.getByEmail(event.getCreated_by().getUserEmail());
        event.setCreated_by(adminUser);
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);
        eventService.createEvent(event);
        return event;
    }

    @PostMapping("/addUser")
    public Events addUsers(@RequestBody UserDto info) {
        Events event = eventService.findById(info.getEventId());

        info.getUserIds().forEach((id) -> {
            Users participant = usv.getById(id);
            participant.getEvents().add(event);
            event.getParticipants().add(participant);
            List<Users> u = event.getParticipants();
            eventService.createEvent(event);
        });

        return event;
    }

    @PostMapping("/discardUser")
    public Events discardUsers(@RequestBody UserDto info) {
        Events event = eventService.findById(info.getEventId());

        info.getUserIds().forEach((id) -> {
            Users participant = usv.getById(id);
            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
        });

        eventService.createEvent(event);

        return event;
    }

    @PostMapping("/displayEvents")
    public List<Events> displayEvents(@RequestBody String Email) {
        eventService.setExpired();
        Users target = usv.getByEmail(Email);
        List<Events> listWithoutDuplicates = target.getEvents();
        List<Events> events = listWithoutDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        if (events != null) {
            return events;
        } else {
            return null;
        }
    }

    @GetMapping("/displayAllEvents")
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

    @PostMapping("/getParticipationMonth")
    public List<Integer> getMonth(@RequestBody int id) {
        AtomicInteger i1 = new AtomicInteger(0);
        AtomicInteger i2 = new AtomicInteger(0);
        AtomicInteger i3 = new AtomicInteger();
        AtomicInteger i4 = new AtomicInteger();


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH);

        Users u = usv.getById(id);

        List<Events> listWithoutDuplicates = u.getEvents();
        List<Events> events = listWithoutDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());


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


    @PostMapping("/displayAbsent")
    public List<Users> displayAbsent(@RequestBody int id) {
        Events event = eventService.findById(id);
        List<Users> users = usv.getAllUsers();
        List<Users> absents = new ArrayList<>();

        users.forEach((users1 -> {
            if (!event.getParticipants().contains(users1)) {
                absents.add(users1);
            }
        }));

        return absents;

    }

    @PostMapping("getUpcomingEvents")
    public List<Events> getUpcoming(int id) {
        Users target = usv.getById(id);

        List<Events> all = target.getEvents();

        List<Events> upcoming = new ArrayList<>();

        upcoming = all.stream().filter(event -> !event.isExpired()).toList();

        Collections.sort(upcoming, new Comparator<Events>() {
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

        return upcoming;


    }

}