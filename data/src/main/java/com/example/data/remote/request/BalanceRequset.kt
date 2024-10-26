package com.example.data.remote.request

import com.google.gson.annotations.SerializedName

data class BalanceRequset(
    @SerializedName("accountNumber")
    val cardNumber: String
)
