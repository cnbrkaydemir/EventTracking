package com.trackevents.service;

import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;
import com.trackevents.model.Users;

import java.util.List;

public interface UserService {

    public UserDto createUser(Users user);

    public UserDto getByEmail(String email);

    public List<UserDto> getAllUsers();

    public List<UserDto> getEventUser(int eventId);

    public UserDto getById(int id);

    public void grantAdmin(int id);

}
