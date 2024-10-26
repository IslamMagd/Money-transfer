package com.example.data.repository

import android.util.Log
import com.example.data.local.sharedPreferences.SharedPreferences
import com.example.data.remote.mapper.auth.LoginParametersMapper
import com.example.data.remote.mapper.auth.LoginResponseMapper
import com.example.data.remote.mapper.auth.SignupParametersMapper
import com.example.data.remote.mapper.auth.SignupResponseMapper
import com.example.data.remote.service.MoneyTransferService
import com.example.data.repository.utils.RepositoryUtils
import com.example.data.repository.utils.RepositoryUtils.parseErrorResponse
import com.example.domain.model.LoginParameters
import com.example.domain.model.LoginResult
import com.example.domain.model.SignUpResult
import com.example.domain.model.SignupParameters
import com.example.domain.model.Status
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val moneyTransferService: MoneyTransferService,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun registerUser(signupParameters: SignupParameters): Flow<Status<SignUpResult?>> {
        return flow {
            emit(Status.Loading)
            val result =
                moneyTransferService.registerUser(SignupParametersMapper().map(signupParameters))
            if (result.isSuccessful) {
                emit(Status.Success(result.body()?.let { SignupResponseMapper().map(it) }))
                Log.d("trace", "${result.body()}")
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { RepositoryUtils.parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }

    override suspend fun loginUser(loginParameters: LoginParameters): Flow<Status<LoginResult?>> {
        return flow {
            emit(Status.Loading)
            val result =
                moneyTransferService.loginUser(LoginParametersMapper().map(loginParameters))
            if (result.isSuccessful) {
                emit(Status.Success(result.body()?.let { LoginResponseMapper().map(it) }))
                Log.d("trace", "${result.body()}")
            } else {
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                Log.d("trace", "$errorMessage")
                emit(Status.Error(errorMessage))
            }
        }
    }

    override fun saveToken(token: String) {
         sharedPreferences.saveToken(token)
    }

    override fun saveCredentials(email: String, password: String) {
        sharedPreferences.saveCredentials(email, password)
    }

    override fun getEmail(): String? {
        return sharedPreferences.getEmail()
    }

    override fun getPassword(): String? {
        return sharedPreferences.getPassword()
    }

    override fun saveUserName(userName: String) {
       return sharedPreferences.saveUserName(userName)
    }

    override fun getUserName(): String? {
        return sharedPreferences.getUserName()
    }

    override suspend fun logoutUser(): Flow<Status<Unit>> {
        return flow {
            emit(Status.Loading)
            val result = moneyTransferService.logoutUser()
            if (result.isSuccessful){
                emit(Status.Success(Unit))
            }
            else{
                val errorBody = result.errorBody()?.string()
                val errorResponse = errorBody?.let { parseErrorResponse(it) }
                val errorMessage = errorResponse?.message ?: "Unknown error"
                emit(Status.Error(errorMessage))
            }
        }
    }
}