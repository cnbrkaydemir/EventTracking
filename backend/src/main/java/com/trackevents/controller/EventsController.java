package com.trackevents.controller;


import com.trackevents.dto.EventDto;
import com.trackevents.dto.ParticipationDto;
import com.trackevents.model.Events;
import com.trackevents.dto.UserDto;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EventsController {

    private final EventService eventService;

    private final UserService userService;


    @PostMapping("/event")
    public ResponseEntity<EventDto> createEvent(@RequestBody Events event) {

        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDto> createEvent(@PathVariable int id) {
        return ResponseEntity.ok(eventService.findById(id));
    }


    @PostMapping("/addUser")
    public ResponseEntity<EventDto> addUsers(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.addUsers(info));
    }

    @PostMapping("/discardUser")
    public ResponseEntity<EventDto> discardUsers(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.discardUsers(info));
    }

    @PostMapping("/event/{userId}")
    public ResponseEntity<List<EventDto>> userEvents(@PathVariable int userId) {
        return ResponseEntity.ok(eventService.getUserEvents(userId));
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> displayEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/month/{eventId}")
    public ResponseEntity<List<Integer>> getMonth(@PathVariable int eventId) {
       return ResponseEntity.ok(eventService.calculateMonth(eventId));
    }

    @GetMapping("/absent/{eventId}")
    public ResponseEntity<List<UserDto>> displayAbsent(@PathVariable int eventId) {
        return ResponseEntity.ok(eventService.findAbsent(eventId));
    }

    @GetMapping("/upcoming/{userId}")
    public ResponseEntity<List<EventDto>> getUpcoming(@PathVariable int userId) {
        return ResponseEntity.ok(eventService.upcomingEvents(userId));
    }


}