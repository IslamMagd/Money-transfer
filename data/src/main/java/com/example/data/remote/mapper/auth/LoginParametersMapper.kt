package com.example.data.remote.mapper.auth

import com.example.data.remote.request.LoginRequest
import com.example.domain.model.LoginParameters
import com.example.domain.utill.Mapper

class LoginParametersMapper : Mapper<LoginParameters, LoginRequest> {
    override fun map(input: LoginParameters): LoginRequest {
        return LoginRequest(email = input.email, password = input.password)
    }
}