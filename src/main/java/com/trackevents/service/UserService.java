package com.trackevents.service;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUsers(int eventId,int userId){
        Events currentEvent=eventRepository.findByEventId(eventId);
        Users targetUser= userRepository.findByUserId(userId);
        currentEvent.getParticipants().add(targetUser);

    }

    public void discardUsers(int eventId,int userId){
        Events currentEvent=eventRepository.findByEventId(eventId);
        Users targetUser= userRepository.findByUserId(userId);
        currentEvent.getParticipants().remove(targetUser);
    }

}
