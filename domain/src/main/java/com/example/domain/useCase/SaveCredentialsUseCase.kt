package com.example.domain.useCase

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SaveCredentialsUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String) =
        authRepository.saveCredentials(email, password)
}