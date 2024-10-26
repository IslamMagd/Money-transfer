package com.example.data.remote.mapper.auth

import com.example.data.remote.response.SignUpResponse
import com.example.domain.model.SignUpResult
import com.example.domain.utill.Mapper

class SignupResponseMapper: Mapper<SignUpResponse, SignUpResult> {
    override fun map(input: SignUpResponse): SignUpResult {
        return SignUpResult(username = input.username, message = input.message)
    }
}