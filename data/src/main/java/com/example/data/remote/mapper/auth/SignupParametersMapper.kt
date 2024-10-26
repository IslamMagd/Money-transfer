package com.example.data.remote.mapper.auth

import com.example.data.remote.request.SignupRequset
import com.example.domain.model.SignupParameters
import com.example.domain.utill.Mapper

class SignupParametersMapper : Mapper<SignupParameters, SignupRequset> {
    override fun map(input: SignupParameters): SignupRequset {
        return SignupRequset(
            username = input.username,
            password = input.password,
            birthdate = input.birthdate,
            email = input.email,
            country = input.country
        )
    }
}