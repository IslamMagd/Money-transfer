package com.example.domain.useCase

import com.example.domain.model.AddCardParameters
import com.example.domain.repository.AccountRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(
        addCardParameters: AddCardParameters
    ) = accountRepository.addCard(addCardParameters)

}