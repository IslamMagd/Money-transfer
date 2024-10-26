package com.example.domain.useCase

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend operator fun invoke(accountNumber: String) = accountRepository.getBalance(accountNumber)
}