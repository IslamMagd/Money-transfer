package com.example.domain.repository

import com.example.domain.model.Status
import com.example.domain.model.TransactionResult
import kotlinx.coroutines.flow.Flow

interface TransacionRepository {
    suspend fun getTransactionsHistory(): Flow<Status<List<TransactionResult>?>>
}