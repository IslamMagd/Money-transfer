package com.example.data.remote.request

data class SignupRequset(
    val username: String,
    val password: String,
    val birthdate: String,
    val email: String,
    val country: String
)
