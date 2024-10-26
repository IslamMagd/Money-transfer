package com.example.domain.useCase

import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class GetCardNumberUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke() = accountRepository.getCardNumber()
}