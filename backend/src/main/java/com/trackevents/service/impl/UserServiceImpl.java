package com.trackevents.service.impl;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;


    public void createUser(Users user){
    userRepository.save(user);
    }

    public Users getByEmail(String email){
        return userRepository.findByUserEmail(email).get(0);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public List<Users> getEventUser(Events events){
        return userRepository.findByEvents(events);
    }

    public Users getById(int id){
        return userRepository.findByUserId(id);
    }
}
