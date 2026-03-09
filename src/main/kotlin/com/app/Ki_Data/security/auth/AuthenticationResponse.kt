package com.app.Ki_Data.security.auth

data class AuthenticationResponse(
    val token: String,
    val message: String
)