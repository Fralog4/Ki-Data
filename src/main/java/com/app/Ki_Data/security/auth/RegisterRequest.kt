package com.app.Ki_Data.security.auth

import com.app.Ki_Data.security.user.Role
import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterRequest(
    val name: String,
    @JsonProperty("last_name") val lastName: String,
    val email: String,
    val password: String,
    val role: Role = Role.USER
)