package com.app.Ki_Data.security.auth;

import com.app.Ki_Data.security.user.Role;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Registers a new user", tags = {"Authentication"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AuthenticationResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request")
            })
    @PostMapping("/registrationUsrOnly")
    public ResponseEntity<AuthenticationResponse> registerUserOnly(@RequestBody RegisterRequest registerRequest){

        Role role= Role.USER;

        return ResponseEntity.ok(authenticationService.register(registerRequest,role));
    }

    @Hidden
    @PostMapping("/registrationADM")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {  //TODO: questa logica va spostata in una classe terza DBInit che usa il service con un metodo @PostConstruct

        Role role= Role.ADMIN;

        return ResponseEntity.ok(authenticationService.register(request, role));
    }

    @Operation(summary = "Authenticate a user", description = "Authenticates a user", tags = {"Authentication"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AuthenticationResponse.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}