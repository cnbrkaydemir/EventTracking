package com.trackevents.service.impl;

import com.trackevents.dto.LoginDto;
import com.trackevents.dto.UserDto;
import com.trackevents.exception.EmailNotFoundException;
import com.trackevents.repository.UserRepository;
import com.trackevents.service.LoginService;
import com.trackevents.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Override
    public UserDto findLoggedInUser(Authentication authentication) {
        return modelMapper.map(userRepository.findByUserEmail(authentication.getName())
                        .orElseThrow(() -> new EmailNotFoundException(authentication.getName()))
                , UserDto.class);
    }

    @Override
    public LoginDto handleLogin(Authentication authentication) {
        String token = tokenService.generateJwt(authentication);
        UserDto  loggedIn = this.findLoggedInUser(authentication);
        return LoginDto.of(loggedIn, token);
    }
}
