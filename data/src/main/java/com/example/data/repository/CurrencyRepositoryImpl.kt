package com.example.data.repository

import com.example.data.remote.mapper.currency.CurrencyResponseMapper
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.utils.RepositoryUtils.parseErrorResponse
import com.example.domain.model.CurrencyConversionResult
import com.example.domain.model.Status
import com.example.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl@Inject constructor(
    private val moneyTransferService: MoneyTransferService
): CurrencyRepository {
    override suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ): Flow<Status<CurrencyConversionResult?>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.convertCurrency( fromCurrency, toCurrency, amount)
            if (result.isSuccessful){
                emit(Status.Success(result.body()?.let { CurrencyResponseMapper().map(it) }))
            }
            else{
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                emit(Status.Error(errorMessage))
            }
        }
    }
}