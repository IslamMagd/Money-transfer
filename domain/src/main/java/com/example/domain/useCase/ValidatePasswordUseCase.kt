package com.example.domain.useCase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean{
        val passwordPattern =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}\$")
        return passwordPattern.matches(password)
    }
}