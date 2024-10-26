package com.example.data.remote.mapper.transactions

import com.example.data.remote.response.TransactionResponse
import com.example.domain.model.TransactionResult
import com.example.domain.utill.Mapper

class TransactionResponseMapper : Mapper<TransactionResponse, TransactionResult> {
    override fun map(input: TransactionResponse): TransactionResult {
        return TransactionResult(
            fromAccountNumber = input.fromAccountNumber,
            toAccountNumber = input.toAccountNumber,
            amount = input.amount,
            transactionDate = input.transactionDate,
            transactionTime = input.transactionTime
        )
    }

    fun mapList(inputList: List<TransactionResponse>): List<TransactionResult> {
        return inputList.map { map(it) }
    }
}
