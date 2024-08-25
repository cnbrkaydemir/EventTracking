package com.trackevents.controller;

import com.trackevents.model.Authority;
import com.trackevents.model.Events;
import com.trackevents.model.UserParticipation;
import com.trackevents.model.Users;
import com.trackevents.repository.AuthorityRepository;
import com.trackevents.service.EventService;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
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


    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository auth;


@PostMapping(path = "/register")
    public Users register(@RequestBody Users user){
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

    @PostMapping(path = "/user")
    public Users displayUser(@RequestBody int id){
        return userService.getById(id);
    }



@GetMapping(path = "/users")
    public List<UserParticipation> displayAll(){
        List<Users> allUsers=userService.getAllUsers();
        allUsers.sort(Comparator.comparingInt(u -> u.getEvents().size()));


        List<UserParticipation> userInfo=new ArrayList<UserParticipation>();

        allUsers.forEach((users)->{
            UserParticipation info = new UserParticipation(users.getUserName()+" "+users.getUserSurname(),users.getUserId(),100*users.getEvents().size()/evs.getAllEvents().size() ,users.getEvents().size() );
            userInfo.add(info);
    });

        return userInfo;
}


    @PostMapping(path = "/displayEventUsers")
    public Events displayAll(@RequestBody int id)
    {
        return evs.findById(id);


    }

    @PostMapping(path = "/admin")
    public Users grantAdmin(@RequestBody int id){
        Users newAdmin=userService.getById(id);
        newAdmin.setUserRole("admin");
        Authority newAuthority= new Authority("ROLE_ADMIN",newAdmin);
        newAdmin.getAuthorities().add(newAuthority);
        auth.save(newAuthority);

        return newAdmin;
    }


}
