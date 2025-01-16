package com.trackevents.controller;

import com.trackevents.exception.EmailNotFoundException;
import com.trackevents.model.Users;
import com.trackevents.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {


    private final UserRepository userRepository;

    @RequestMapping("/api")
    public Users getUserDetailsAfterLogin(Principal user){
        return userRepository.findByUserEmail(user.getName())
                .orElseThrow(() -> new EmailNotFoundException(user.getName()));

    }
}
