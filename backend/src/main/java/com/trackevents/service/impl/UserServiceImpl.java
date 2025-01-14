package com.trackevents.service.impl;

import com.trackevents.dto.UserDto;
import com.trackevents.model.Authority;
import com.trackevents.model.Events;
import com.trackevents.model.Users;
import com.trackevents.repository.AuthorityRepository;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository auth;

    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(Users user){
        user.setUserRole("user");
        Authority userAuthority= new Authority("ROLE_USER",user);

        Set<Authority> userAuthorities= new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);
        auth.save(userAuthority);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getByEmail(String email){
        return modelMapper.map(userRepository.findByUserEmail(email).get(0),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map((user)-> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public List<UserDto> getEventUser(Events events){
        return userRepository.findByEvents(events).stream().map((user)-> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto getById(int id){
        return modelMapper.map(userRepository.findByUserId(id),UserDto.class);
    }

    @Override
    public void grantAdmin(int id) {
        Users newAdmin = userRepository.findByUserId(id);
        newAdmin.setUserRole("admin");
        Authority newAuthority= new Authority("ROLE_ADMIN",newAdmin);
        newAdmin.getAuthorities().add(newAuthority);
        auth.save(newAuthority);
    }
}
