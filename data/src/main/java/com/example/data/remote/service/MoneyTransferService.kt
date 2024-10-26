package com.example.data.remote.service

import com.example.data.remote.request.AddCardRequest
import com.example.data.remote.request.ChangePasswordRequest
import com.example.data.remote.request.EditProfileRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignupRequset
import com.example.data.remote.request.TransferMoneyRequst
import com.example.data.remote.request.VerifyOtpRequest
import com.example.data.remote.response.BalanceResponse
import com.example.data.remote.response.AddCardResponse
import com.example.data.remote.response.ConvertCurrencyResponse
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.ProfileResponse
import com.example.data.remote.response.SignUpResponse
import com.example.data.remote.response.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoneyTransferService {

    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>


}