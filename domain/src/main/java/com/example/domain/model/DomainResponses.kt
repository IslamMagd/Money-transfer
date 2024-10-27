package com.example.domain.model

data class AddCardResult(
    val cardholderName: String,
    val cardNumber: String,
    val balance: String
)

data class Balance(
    val balance: Double
)

data class CurrencyConversionResult(
    val rate: Double,
    val convertedAmount: Double
)
data class LoginResult(
    val token: String
)

data class SignUpResult(
    val username: String,
    val  message: String?
)


data class TransactionResult(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val amount: Double,
    val transactionDate: String,
    val transactionTime: String,
)
