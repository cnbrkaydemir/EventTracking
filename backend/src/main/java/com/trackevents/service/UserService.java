package com.trackevents.service;

import com.trackevents.dto.UserDto;
import com.trackevents.model.Events;
import com.trackevents.model.Users;

import java.util.List;

public interface UserService {

    UserDto createUser(Users user);

    UserDto createAdmin(Users user);

    List<UserDto> getAllUsers();

    List<UserDto> getParticipants(int eventId);

    UserDto getById(int id);


}
