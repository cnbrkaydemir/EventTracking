package com.trackevents.controller;

import com.trackevents.dto.LoginDto;
import com.trackevents.dto.UserDto;

import com.trackevents.service.LoginService;
import com.trackevents.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/token")
    public ResponseEntity<LoginDto> token(Authentication authentication){
        return ResponseEntity.ok(loginService.handleLogin(authentication));
    }

}
