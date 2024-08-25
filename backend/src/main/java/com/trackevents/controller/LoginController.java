package com.trackevents.controller;

import com.trackevents.model.Users;
import com.trackevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user")
    public Users getUserDetailsAfterLogin(Principal user){
        List<Users> users=userRepository.findByUserEmail(user.getName());

        if(users.size()>0){
            return users.get(0);
        }

        else
            return null;
    }
}
