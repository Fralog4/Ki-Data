package com.app.Ki_Data.security.auth

import com.app.Ki_Data.security.jwtConfig.JwtService
import com.app.Ki_Data.security.user.Role
import com.app.Ki_Data.security.user.User
import com.app.Ki_Data.security.user.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

class AuthenticationService(

    private val repository: UserRepository,
    private val service: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager
) {


    fun register(request: RegisterRequest, role: Role): AuthenticationResponse {

        val user = User(
            name = request.name,
            lastName = request.lastName,
            role = request.role,
            email = request.email,
            password = passwordEncoder.encode(request.password),
        )

        repository.save<User>(user)
        val jwtToken = service.generateToken(user)
        return AuthenticationResponse(
            token = jwtToken,
            message = "User registered"
        )
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {

        return try {

            val user = repository.findByEmail(request.email)
                ?: run { throw UsernameNotFoundException("User not found with email: ${request.email}") }
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
            val token = service.generateToken(user)
            AuthenticationResponse(token, "User authenticated")

        } catch (e: Exception) {
            throw AuthenticationServiceException("Authentication failed: ${e.message}", e)
        }
    }
}
