package com.example.data.remote.request

data class VerifyOtpRequest(
    val otp: String,
    val accountNumber: String
)
