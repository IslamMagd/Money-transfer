package com.example.domain.model

data class AddCardParameters(
    val cardholderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String,
    val isActive: Boolean,
    val cardCurrency: String,
    val balance: Double
)

data class BalanceParameters(
    val cardNumber: String
)

data class ChangePasswordParameters(
    val currentPassword: String,
    val newPassword: String
)

data class EditProfileParameters(
    val username: String,
    val birthdate: String,
    val country: String
)

data class LoginParameters(
    val email: String,
    val password: String
)

data class SignupParameters(
    val username: String,
    val password: String,
    val birthdate: String,
    val email: String,
    val country: String
)

data class TransferMoneyParameters(
    val fromCardNumber : String,
    val toCardNumber: String,
    val amount: String
)

data class VerifyOtpParameters(
    val otp: String,
    val accountNumber: String
)

