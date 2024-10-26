package com.example.domain.useCase

import com.example.domain.model.SignupParameters
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        signupParameters: SignupParameters
    ) = authRepository.registerUser(signupParameters)
}