package com.trackevents.service.impl;

import com.trackevents.dto.UserDto;
import com.trackevents.exception.DuplicateUserException;
import com.trackevents.exception.EmailNotFoundException;
import com.trackevents.exception.EventNotFoundException;
import com.trackevents.exception.UserNotFoundException;
import com.trackevents.model.Authority;
import com.trackevents.model.Users;
import com.trackevents.repository.AuthorityRepository;
import com.trackevents.repository.EventRepository;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final AuthorityRepository auth;

    private final ModelMapper modelMapper;

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public UserDto createUser(Users user){

        if(userRepository.findByUserEmail(user.getUserEmail()).isPresent()){
            throw  new DuplicateUserException(user.getUserEmail());
        }

        user.setUserRole("user");
        Authority userAuthority= new Authority("ROLE_USER",user);

        Set<Authority> userAuthorities= new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    @Cacheable(value = "user")
    public UserDto getByEmail(String email){
        return modelMapper.map(userRepository.findByUserEmail(email)
                        .orElseThrow(() -> new EmailNotFoundException(email))
                ,UserDto.class);
    }

    @Override
    @Cacheable(value = "user")
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map((user)-> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    @Cacheable(value = "event_user")
    public List<UserDto> getEventUser(int eventId){
        return eventRepository.findByEventId(eventId)
                .orElseThrow(()-> new EventNotFoundException(eventId))
                .getParticipants()
                .stream().map((user)-> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    @Cacheable(value = "user")
    public UserDto getById(int id){
        return modelMapper.map(userRepository.findByUserId(id)
                .orElseThrow(() -> new UserNotFoundException(id))
                ,UserDto.class);
    }

    @Override
    @CacheEvict(value = "user", allEntries = true)
    public void grantAdmin(int id) {
        Users newAdmin = userRepository.findByUserId(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        newAdmin.setUserRole("admin");
        Authority newAuthority= new Authority("ROLE_ADMIN",newAdmin);
        newAdmin.getAuthorities().add(newAuthority);
        auth.save(newAuthority);
    }
}
