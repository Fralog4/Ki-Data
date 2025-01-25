package com.app.Ki_Data.security.auth;

import com.app.Ki_Data.security.jwtConfig.JwtService;
import com.app.Ki_Data.security.user.Role;
import com.app.Ki_Data.security.user.User;
import com.app.Ki_Data.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request, Role role) {
        var user = User.builder()
                .name(request.getName())
                .last_name(request.getLast_name())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        log.debug("The user is : " + user.getName()+" and has the role of "+user.getRole());
        repository.save(user);
        log.info("User registered: " + user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        var user=repository.findByEmail(authenticationRequest.getEmail())
                .orElseGet(()->{
                    log.error("User with email " + authenticationRequest.getEmail() + " not found");
                    return null;
                });
        var jwtToken= service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
