package com.trackevents.controller;

import com.trackevents.model.Authority;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService usv){
        this.userService=usv;
    }

@PostMapping(path = "/register")
    public Users signUp(@RequestBody Users user){
        user.setUserRole("user");
        Authority userAuthority= new Authority("ROLE_USER",user);
        user.getAuthorities().add(userAuthority);
        userService.createUser(user);
        return user;
}




@GetMapping(path = "/displayAllUsers")
    public List<Users> displayAll(){
        return userService.getAllUsers();
}


    @GetMapping(path = "/displayEventUsers")
    public List<Users> displayAll(@RequestBody Events events){
        return userService.getEventUser(events);
    }


}
