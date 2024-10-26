package com.example.domain.useCase

class ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean{
        val emailPattern = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return emailPattern.matches(email)
    }
}