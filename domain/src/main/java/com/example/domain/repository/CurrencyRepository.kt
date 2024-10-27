package com.example.domain.repository

import com.example.domain.model.CurrencyConversionResult
import com.example.domain.model.Status
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Flow<Status<CurrencyConversionResult?>>
}