package com.trackevents.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface TokenService {

    String generateJwt(Authentication auth);

    String populateAuthorities(Collection<? extends GrantedAuthority> collection);
}
