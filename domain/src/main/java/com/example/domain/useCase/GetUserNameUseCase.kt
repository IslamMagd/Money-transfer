package com.example.domain.useCase

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getUserName()
}