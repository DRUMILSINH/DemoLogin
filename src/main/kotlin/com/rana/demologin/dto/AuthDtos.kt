package com.rana.demologin.dto

data class LoginRequest(
    val username: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val fullName: String
)

data class AuthResponse(
    val token: String
) 