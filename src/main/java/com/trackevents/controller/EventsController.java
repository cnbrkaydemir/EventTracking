package com.trackevents.controller;


import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EventsController {

    private final EventService eventService;

    @Autowired
    public EventsController(EventService evs){
        this.eventService=evs;
    }

    @PostMapping("/createEvent")
    public Events createEvent(@RequestBody Events event, HttpSession httpSession){
        Users adminUser=(Users) httpSession.getAttribute("userdetails");
        event.setCreated_by(adminUser);
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

    @GetMapping("/displayEvents")
    public List<Events> displayEvents(HttpSession httpSession){
        Users target= (Users) httpSession.getAttribute("userdetails");
        eventService.setExpired();
        List<Events> events=target.getEvents();

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
