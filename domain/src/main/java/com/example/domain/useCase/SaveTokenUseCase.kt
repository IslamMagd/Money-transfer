package com.example.domain.useCase

import com.example.domain.model.SignupParameters
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
     operator fun invoke(token: String) = authRepository.saveToken(token)
}