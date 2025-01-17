package com.trackevents.service;

import com.trackevents.dto.LoginDto;
import com.trackevents.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface LoginService {

    UserDto findLoggedInUser(Authentication authentication);

    LoginDto handleLogin(Authentication authentication);
}
