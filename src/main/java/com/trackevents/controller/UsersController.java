package com.trackevents.controller;

import com.trackevents.model.Authority;
import com.trackevents.model.Events;
import com.trackevents.model.UserParticipation;
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

import java.util.*;

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
    public List<UserParticipation> displayAll(){
        List<Users> allUsers=userService.getAllUsers();
        Collections.sort(allUsers, new Comparator<Users>() {
            @Override
            public int compare(Users o1, Users o2) {
                if(o1.getEvents().size()>o2.getEvents().size()){
                    return -1;
                }
                else if(o1.getEvents().size()==o2.getEvents().size()){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
    while(allUsers.size()!=5){
        allUsers.remove(allUsers.size()-1);
    }
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


}
