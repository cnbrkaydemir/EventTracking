package com.trackevents.controller;

import com.trackevents.dto.EventDto;
import com.trackevents.dto.UserDto;
import com.trackevents.model.Authority;
import com.trackevents.model.Users;
import com.trackevents.repository.AuthorityRepository;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    private final EventService evs;



    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> register(@RequestBody Users user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserDto> displayUser(@RequestBody int id){
        return ResponseEntity.ok(userService.getById(id));
    }


    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> displayAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PostMapping(path = "/events")
    public ResponseEntity<EventDto> displayAll(@RequestBody int id) {
        return ResponseEntity.ok(evs.findById(id));
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<String> grantAdmin(@RequestBody int id){
        userService.grantAdmin(id);
        return ResponseEntity.ok("Granted Admin Successfully");
    }


}
