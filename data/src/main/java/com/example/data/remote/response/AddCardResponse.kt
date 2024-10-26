package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddCardResponse(
    @SerializedName("accountName")
    val cardholderName: String,
    @SerializedName("accountNumber")
    val cardNumber: String,
    val balance: String
)
