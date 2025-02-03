package com.trackevents.controller;


import com.trackevents.dto.UserDto;
import com.trackevents.model.Users;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;


    @PostMapping(path = "/user")
    public ResponseEntity<UserDto> createUser(@RequestBody Users user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable int userId){
        return ResponseEntity.ok(userService.getById(userId));
    }


    @GetMapping(path = "/user")
    public ResponseEntity<List<UserDto>> displayAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping(path = "/participants/{eventId}")
    public ResponseEntity<List<UserDto>> displayParticipantsOfEvent(@PathVariable int eventId) {
        return ResponseEntity.ok(userService.getParticipants(eventId));
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<UserDto> grantAdmin(@RequestBody Users user){
        return ResponseEntity.ok(userService.createAdmin(user));
    }

}
