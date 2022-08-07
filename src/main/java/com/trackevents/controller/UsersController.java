package com.trackevents.controller;

import com.trackevents.model.Authority;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.AuthorityRepository;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UsersController {

    private final UserService userService;
    private final EventService evs;


    private PasswordEncoder passwordEncoder;

    private AuthorityRepository auth;
    @Autowired
    public UsersController(UserService usv, EventService evs, PasswordEncoder pwe, AuthorityRepository auth){
        this.userService=usv;
        this.evs=evs;
        this.passwordEncoder=pwe;
        this.auth=auth;
    }

@PostMapping(path = "/register")
    public Users signUp(@RequestBody Users user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserRole("user");
        Authority userAuthority= new Authority("ROLE_USER",user);

        Set<Authority> userAuthorities= new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);
        userService.createUser(user);
         auth.save(userAuthority);
        return user;
}

    @PostMapping(path = "/displayUser")
    public Users displayUser(@RequestBody int id){
        return userService.getById(id);
    }



@GetMapping(path = "/displayAllUsers")
    public List<Users> displayAll(){
        return userService.getAllUsers();
}


    @PostMapping(path = "/displayEventUsers")
    public Events displayAll(@RequestBody int id)
    {
        return evs.findById(id);


    }


}
