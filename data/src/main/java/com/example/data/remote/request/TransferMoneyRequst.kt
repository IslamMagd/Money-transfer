package com.example.data.remote.request

import com.google.gson.annotations.SerializedName

data class TransferMoneyRequst(
    @SerializedName("fromAccountNumber")
    val fromCardNumber : String,
    @SerializedName("toAccountNumber")
    val toCardNumber: String,
    val amount: String
)


