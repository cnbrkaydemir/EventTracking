package com.trackevents.service.impl;

import com.trackevents.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


    @Service
    @RequiredArgsConstructor
    public class TokenServiceImpl implements TokenService {

        private final JwtEncoder jwtEncoder;

        private final JwtDecoder jwtDecoder;


        @Override
        public String generateJwt(Authentication auth){

            Instant now = Instant.now();


            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .subject(auth.getName())
                    .claim("authorities", populateAuthorities(auth.getAuthorities()))
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        }


        @Override
        public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
            Set<String> authoritiesSet = new HashSet<>();
            for (GrantedAuthority authority : collection) {
                authoritiesSet.add(authority.getAuthority());
            }
            return String.join(",", authoritiesSet);
        }

    }
