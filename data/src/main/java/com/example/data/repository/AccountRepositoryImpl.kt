package com.example.data.repository

import android.util.Log
import com.example.data.local.sharedPreferences.SharedPreferences
import com.example.data.remote.mapper.account.BalanceResponseMapper
import com.example.data.remote.mapper.account.CardParametersMapper
import com.example.data.remote.mapper.account.CardResponseMapper
import com.example.data.remote.mapper.account.OtpParametersMapper
import com.example.data.remote.mapper.account.TransferParametersMapper
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.utils.RepositoryUtils.parseErrorResponse
import com.example.domain.model.AddCardParameters
import com.example.domain.model.AddCardResult
import com.example.domain.model.Balance
import com.example.domain.model.Status
import com.example.domain.model.TransferMoneyParameters
import com.example.domain.model.VerifyOtpParameters
import com.example.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val moneyTransferService: MoneyTransferService,
    private val sharedPreferences: SharedPreferences
) : AccountRepository {
    override suspend fun addCard(
        addCardParameters: AddCardParameters
    ): Flow<Status<AddCardResult?>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.addCard(
                CardParametersMapper().map(addCardParameters)
            )
            if (result.isSuccessful) {
                emit(Status.Success(result.body()?.let { CardResponseMapper().map(it) }))
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }

    override suspend fun verifyOtp(verifyOtpParameters: VerifyOtpParameters): Flow<Status<Unit>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.verifyOtp(
                OtpParametersMapper().map(verifyOtpParameters)
            )
            if (result.isSuccessful) {
                Log.d("trace", "success")
                emit(Status.Success(Unit))
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }


    override suspend fun getBalance(accountNumber: String): Flow<Status<Balance?>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.getBalance(accountNumber)
            if (result.isSuccessful) {
                emit(Status.Success(result.body()?.let { BalanceResponseMapper().map(it) }))
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }

    override fun saveCardInformation(cardholderName: String, cardNumber: String, balance: String) {
        sharedPreferences.saveCardInformation(cardholderName, cardNumber, balance)
    }

    override fun getCardNumber(): String? {
        return sharedPreferences.getCardNumber()
    }

    override fun getCardHolderName(): String? {
        return sharedPreferences.getCardHolderName()
    }

    override fun getSavedBalance(): String? {
        return sharedPreferences.getSavedBalance()
    }
}