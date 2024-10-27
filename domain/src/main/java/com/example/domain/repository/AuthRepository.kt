package com.example.domain.repository

import com.example.domain.model.LoginParameters
import com.example.domain.model.LoginResult
import com.example.domain.model.SignUpResult
import com.example.domain.model.SignupParameters
import com.example.domain.model.Status
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun registerUser(signupParameters: SignupParameters): Flow<Status<SignUpResult?>>

    suspend fun loginUser(loginParameters: LoginParameters): Flow<Status<LoginResult?>>

    fun saveToken(token: String)

    fun saveCredentials(email: String, password: String)

    fun getEmail(): String?

    fun getPassword(): String?

    fun saveUserName(userName: String)

    fun getUserName(): String?

    suspend fun logoutUser(): Flow<Status<Unit>>
}