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
    public ResponseEntity<EventDto> findEventById(@PathVariable int id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventDto>> displayAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }


    @PostMapping("/add")
    public ResponseEntity<EventDto> addUsersToEvent(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.addUsers(info));
    }

    @PostMapping("/discard")
    public ResponseEntity<EventDto> discardUsersFromEvent(@RequestBody ParticipationDto info) {
        return ResponseEntity.ok(eventService.discardUsers(info));
    }

    @GetMapping("/attending/{userId}")
    public ResponseEntity<List<EventDto>> getUserEvents(@PathVariable int userId) {
        return ResponseEntity.ok(eventService.getUserEvents(userId));
    }


    @GetMapping("/month/{userId}")
    public ResponseEntity<List<Integer>> getUpcomingEventsByMonth(@PathVariable int userId) {
       return ResponseEntity.ok(eventService.calculateMonthlyDistribution(userId));
    }

    @GetMapping("/absent/{eventId}")
    public ResponseEntity<List<UserDto>> displayAbsentUsers(@PathVariable int eventId) {
        return ResponseEntity.ok(eventService.findAbsent(eventId));
    }

    @GetMapping("/upcoming/{userId}")
    public ResponseEntity<List<EventDto>> getUpcomingEventsOfUser(@PathVariable int userId) {
        return ResponseEntity.ok(eventService.upcomingEvents(userId));
    }


}