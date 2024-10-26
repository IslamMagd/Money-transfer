package com.example.data.remote.mapper.auth

import com.example.data.remote.response.LoginResponse
import com.example.domain.model.LoginResult
import com.example.domain.utill.Mapper

class LoginResponseMapper: Mapper<LoginResponse, LoginResult> {
    override fun map(input: LoginResponse): LoginResult {
        return LoginResult(token = input.token)
    }
}