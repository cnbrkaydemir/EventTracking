package com.trackevents.controller;


import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
        Users adminUser = event.getCreated_by();
        event.getParticipants().add(adminUser);
        adminUser.getEvents().add(event);
        return event;
    }

    @PostMapping("/addUser")
    public void addUsers(@RequestBody Users user, @RequestBody Events events){
        eventService.addUsers(events.getEventId(),user.getUserId());
    }

    @PostMapping("/discardUser")
    public void discardUsers(@RequestBody Users user, @RequestBody Events events){
        eventService.discardUsers(events.getEventId(),user.getUserId());
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
    public List<Events> displayEvents(){
        eventService.setExpired();
        List<Events> events=eventService.getAllEvents();

        if(events!=null){
            return events;
        }
        else{
            return null;
        }
    }





}
