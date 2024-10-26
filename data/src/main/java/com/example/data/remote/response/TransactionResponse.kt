package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val amount: Double,
    @SerializedName("transactionData")
    val transactionDate: String,
    val transactionTime: String,
)
