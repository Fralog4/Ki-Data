package com.app.Ki_Data.security.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
)