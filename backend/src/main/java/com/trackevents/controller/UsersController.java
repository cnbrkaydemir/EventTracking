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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    private final EventService eventService;



    @PostMapping(path = "/user")
    public ResponseEntity<UserDto> register(@RequestBody Users user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserDto> displayUser(@PathVariable int id){
        return ResponseEntity.ok(userService.getById(id));
    }


    @GetMapping(path = "/user")
    public ResponseEntity<List<UserDto>> displayAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping(path = "/eventUser/{id}")
    public ResponseEntity<List<UserDto>> displayEventUsers(@PathVariable int id) {
        return ResponseEntity.ok(userService.getEventUser(id));
    }

    @PostMapping(path = "/admin/{id}")
    public ResponseEntity<String> grantAdmin(@PathVariable int id){
        userService.grantAdmin(id);
        return ResponseEntity.ok("Granted Admin Successfully");
    }


}
