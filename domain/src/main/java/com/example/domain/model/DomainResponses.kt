package com.example.domain.model

data class AddCardResult(
    val cardholderName: String,
    val cardNumber: String,
    val balance: String
)

data class LoginResult(
    val token: String
)

data class SignUpResult(
    val username: String,
    val  message: String?
)

