package com.example.domain.useCase

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(userName: String) = authRepository.saveUserName(userName)
}