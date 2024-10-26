package com.example.domain.useCase

import com.example.domain.model.VerifyOtpParameters
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(
        verifyOtpParameters: VerifyOtpParameters
    ) = accountRepository.verifyOtp(verifyOtpParameters)
}