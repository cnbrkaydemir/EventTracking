package com.trackevents.service;

import com.trackevents.model.Events;
import com.trackevents.model.Users;

import java.util.List;

public interface UserService {
    public void createUser(Users user);


    public Users getByEmail(String email);

    public List<Users> getAllUsers();

    public List<Users> getEventUser(Events events);

    public Users getById(int id);

}
