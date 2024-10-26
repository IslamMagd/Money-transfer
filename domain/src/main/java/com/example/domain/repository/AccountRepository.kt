package com.example.domain.repository

import com.example.domain.model.AddCardParameters
import com.example.domain.model.AddCardResult
import com.example.domain.model.Balance
import com.example.domain.model.Status
import com.example.domain.model.TransferMoneyParameters
import com.example.domain.model.VerifyOtpParameters
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun addCard(addCardParameters: AddCardParameters): Flow<Status<AddCardResult?>>

    suspend fun verifyOtp(verifyOtpParameters: VerifyOtpParameters): Flow<Status<Unit>>


    fun saveCardInformation(cardholderName: String, cardNumber: String, balance: String)
    fun getCardNumber(): String?
    fun getCardHolderName(): String?
    fun getSavedBalance(): String?
}