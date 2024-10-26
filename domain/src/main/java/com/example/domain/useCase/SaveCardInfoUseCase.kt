package com.example.domain.useCase

import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class SaveCardInfoUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(cardHolderName: String, cardNumber: String, balance: String) =
        accountRepository.saveCardInformation(cardHolderName, cardNumber, balance)
}