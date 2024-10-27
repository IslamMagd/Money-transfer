package com.example.domain.useCase

import com.example.domain.model.TransferMoneyParameters
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class TransferMoneyUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(
        transferMoneyParameters: TransferMoneyParameters
    ) = accountRepository.transferMoney(transferMoneyParameters)
}