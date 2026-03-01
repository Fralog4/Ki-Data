package com.app.Ki_Data.security.auth

import com.app.Ki_Data.security.user.Role
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
class AuthenticationController(private val service: AuthenticationService) {
    @Operation(
        summary = "Register a new user",
        description = "Registers a new user",
        tags = ["Authentication"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "User registered successfully",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AuthenticationResponse::class)
            )]
        ), ApiResponse(responseCode = "400", description = "Invalid request")]
    )
    @PostMapping("/registrationUsrOnly")
    fun registerUserOnly(@RequestBody request: RegisterRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(service.register(request, Role.USER))


    @Hidden
    @PostMapping("/registrationADM")
    fun registerAdmin(@RequestBody request: RegisterRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(service.register(request, Role.ADMIN))

    @Operation(
        summary = "Authenticate a user",
        description = "Authenticates a user",
        tags = ["Authentication"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "User authenticated successfully",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = AuthenticationResponse::class)
            )]
        ), ApiResponse(responseCode = "400", description = "Invalid request")]
    )
    @PostMapping("/authentication")
    fun register(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> =
        ResponseEntity.ok(service.authenticate(request))

}