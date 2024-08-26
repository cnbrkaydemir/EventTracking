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

    @PostMapping("/events/{id}")
    public ResponseEntity<List<EventDto>> userEvents(@PathVariable int id) {
        return ResponseEntity.ok(eventService.getUserEvents(id));
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> displayEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/month")
    public ResponseEntity<List<Integer>> getMonth(@RequestBody int id) {
       return ResponseEntity.ok(eventService.calculateMonth(id));
    }


    @PostMapping("/absent")
    public ResponseEntity<List<UserDto>> displayAbsent(@RequestBody int id) {
        return ResponseEntity.ok(eventService.findAbsent(id));
    }

    @PostMapping("/upcoming")
    public ResponseEntity<List<EventDto>> getUpcoming(@RequestBody int id) {
        return ResponseEntity.ok(eventService.upcomingEvents(id));
    }


}