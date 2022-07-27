package com.trackevents.service;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Autowired
    public UserService(UserRepository userRepository,EventRepository eventRepository){
        this.userRepository=userRepository;
        this.eventRepository=eventRepository;
    }

    public void createUser(Users user){
    userRepository.save(user);
    }



    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public List<Users> getEventUser(Events events){
        return userRepository.findByEvents(events);
    }

}
