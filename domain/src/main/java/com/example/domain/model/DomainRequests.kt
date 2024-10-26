package com.example.domain.model


data class LoginParameters(
    val email: String,
    val password: String
)

data class SignupParameters(
    val username: String,
    val password: String,
    val birthdate: String,
    val email: String,
    val country: String
)

