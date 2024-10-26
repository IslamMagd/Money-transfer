package com.example.domain.model


data class LoginResult(
    val token: String
)

data class SignUpResult(
    val username: String,
    val  message: String?
)

