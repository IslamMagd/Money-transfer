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
    @POST("/api/register")
    suspend fun registerUser(@Body signupRequset: SignupRequset): Response<SignUpResponse>

    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/api/logout")
    suspend fun logoutUser(): Response<Void>

    @POST("/api/update-user")
    suspend fun editProfile(@Body editProfileRequest: EditProfileRequest): Response<Void>

    @GET("/api/user")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("/api/reset-password")
    suspend fun resetPassword(@Body changePasswordRequest: ChangePasswordRequest): Response<Void>


    @POST("/api/create_account")
    suspend fun addCard(@Body addCardRequest: AddCardRequest): Response<AddCardResponse>

    @POST("/api/transfer")
    suspend fun transferMoney(@Body transferMoneyRequst: TransferMoneyRequst): Response<Void>

    @GET("/api/transactions")
    suspend fun getTransactions(): Response<List<TransactionResponse>>

    @GET("/api/balance/{accountNumber}")
    suspend fun getBalance(@Path("accountNumber") accountNumber: String): Response<BalanceResponse>

    @POST("/api/verify-otp")
    suspend fun verifyOtp(@Body verifyOtpRequest: VerifyOtpRequest): Response<Void>

    @GET("/api/convert")
    suspend fun convertCurrency(
        @Query("from") fromCurrency: String,
        @Query("to") toCurrency: String,
        @Query("amount") amount: Double,
    ): Response<ConvertCurrencyResponse>

}