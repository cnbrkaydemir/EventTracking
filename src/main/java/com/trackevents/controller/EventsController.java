package com.trackevents.controller;


import com.trackevents.model.Events;
import com.trackevents.model.UserDto;
import com.trackevents.model.Users;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventsController {

    private final EventService eventService;

    private final UserService usv;
    @Autowired
    public EventsController(EventService evs,UserService usv){
        this.eventService=evs;
        this.usv=usv;
    }

    @PostMapping("/createEvent")
    public Events createEvent(@RequestBody Events event){
        Users adminUser=usv.getByEmail(event.getCreated_by().getUserEmail());
        event.setCreated_by(adminUser);
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);
        eventService.createEvent(event);
        return event;
    }

    @PostMapping("/addUser")
    public Events addUsers(@RequestBody UserDto info){
        Events event=eventService.findById(info.getEventId());

        info.getUserIds().forEach((id)->{
            Users participant=usv.getById(id);
            participant.getEvents().add(event);
            event.getParticipants().add(participant);
            List <Users> u=event.getParticipants();
            eventService.createEvent(event);
        });

        return event;
    }

    @PostMapping("/discardUser")
    public Events discardUsers(@RequestBody UserDto info){
        Events event=eventService.findById(info.getEventId());

        info.getUserIds().forEach((id)->{
            Users participant=usv.getById(id);
            event.getParticipants().remove(participant);
            participant.getEvents().remove(event);
        });

        eventService.createEvent(event);

        return event;
    }

    @PostMapping("/displayEvents")
    public List<Events> displayEvents(@RequestBody String Email){
        eventService.setExpired();
        Users target=usv.getByEmail(Email);
        List<Events> listWithoutDuplicates=target.getEvents();
        List<Events> events = listWithoutDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        if(events!=null){
            return events;
        }
        else{
            return null;
        }
    }

    @GetMapping("/displayAllEvents")
    public List<Events> displayEvents() {
        eventService.setExpired();
        List<Events> events = eventService.getAllEvents();

        if (events != null) {
            return events;
        } else {
            return null;
        }
    }
        @PostMapping("/displayAbsent")
        public List<Users> displayAbsent(@RequestBody int id){
            Events event= eventService.findById(id);
            List<Users> users=usv.getAllUsers();
            List <Users> absents= new ArrayList<>();

            users.forEach((users1 -> {
                if(!event.getParticipants().contains(users1)){
                    absents.add(users1);
                }
            }));

    return absents;

    }





}
