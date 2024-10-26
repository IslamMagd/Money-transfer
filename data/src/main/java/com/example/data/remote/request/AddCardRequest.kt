package com.example.data.remote.request

import com.google.gson.annotations.SerializedName

data class AddCardRequest(
    @SerializedName("accountName")
    val cardholderName: String,
    @SerializedName("accountNumber")
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String,
    val isActive: Boolean,
    @SerializedName("accountCurrency")
    val cardCurrency: String,
    val balance: Double
)
