package com.app.Ki_Data.security.auth;

import com.app.Ki_Data.security.jwtConfig.JwtService;
import com.app.Ki_Data.security.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AutheticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .name(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken= service.generateToken(user);
        /***var admin= User.builder()
                .name(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        repository.save(admin);
        var jwtTokenAdmin= service.generateToken(admin);***/
        return AutheticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AutheticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        var user=repository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken= service.generateToken(user);
        return AutheticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
