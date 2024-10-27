package com.example.domain.useCase

import com.example.domain.repository.CurrencyRepository
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    suspend operator fun invoke(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ) = currencyRepository.convertCurrency(fromCurrency, toCurrency, amount)
}