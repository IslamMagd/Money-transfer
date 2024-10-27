package com.example.data.repository

import android.util.Log
import com.example.data.remote.mapper.transactions.TransactionResponseMapper
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.utils.RepositoryUtils.parseErrorResponse
import com.example.domain.model.Status
import com.example.domain.model.TransactionResult
import com.example.domain.repository.TransacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransacionRepositoryImp @Inject constructor(
    private val moneyTransferService: MoneyTransferService
) : TransacionRepository {
    override suspend fun getTransactionsHistory(): Flow<Status<List<TransactionResult>?>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.getTransactions()
            if (result.isSuccessful) {
                emit(Status.Success(result.body()?.let { TransactionResponseMapper().mapList(it) }))
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }
}