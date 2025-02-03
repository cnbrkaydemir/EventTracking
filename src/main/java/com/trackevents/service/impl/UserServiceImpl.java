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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final AuthorityRepository authorityRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @CacheEvict(value = "all_user", allEntries = true)
    public UserDto createUser(Users user){

        if(userRepository.findByUserEmail(user.getUserEmail()).isPresent()){
            throw  new DuplicateUserException(user.getUserEmail());
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        user.setCreatedBy("admin");

        user.setCreatedDate(new Date());

        user.setUserRole("user");


        Authority userAuthority= new Authority("USER",user);

        Set<Authority> userAuthorities= new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);


        return modelMapper.map(userRepository.save(user), UserDto.class);
    }



    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "all_user")
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map((user)-> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "event_user")
    public List<UserDto> getParticipants(int eventId){
        return eventRepository.findByEventId(eventId)
                .orElseThrow(()-> new EventNotFoundException(eventId))
                .getParticipants()
                .stream().map((user)-> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "user")
    public UserDto getById(int id){
        return modelMapper.map(userRepository.findByUserId(id)
                        .orElseThrow(() -> new UserNotFoundException(id))
                ,UserDto.class);
    }

    @Override
    @Transactional
    @CacheEvict("all_user")
    public UserDto createAdmin(Users user) {
        if(userRepository.findByUserEmail(user.getUserEmail()).isPresent()){
            throw  new DuplicateUserException(user.getUserEmail());
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        user.setCreatedBy("admin");

        user.setCreatedDate(new Date());

        user.setUserRole("admin");


        Authority userAuthority= new Authority("ADMIN",user);

        Set<Authority> userAuthorities= new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setAuthorities(userAuthorities);


        return modelMapper.map(userRepository.save(user), UserDto.class);
    }
}
