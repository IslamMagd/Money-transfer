package com.example.domain.useCase

import com.example.domain.model.LoginParameters
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        loginParameters: LoginParameters
    ) = authRepository.loginUser(loginParameters)

}