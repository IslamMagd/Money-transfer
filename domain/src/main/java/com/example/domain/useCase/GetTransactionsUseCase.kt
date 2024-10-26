package com.example.domain.useCase

import com.example.domain.repository.TransacionRepository
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransacionRepository
) {
    suspend operator fun invoke() = transactionRepository.getTransactionsHistory()
}