package com.example.data.remote.mapper.account

import com.example.data.remote.request.VerifyOtpRequest
import com.example.domain.model.VerifyOtpParameters
import com.example.domain.utill.Mapper

class OtpParametersMapper: Mapper<VerifyOtpParameters, VerifyOtpRequest> {
    override fun map(input: VerifyOtpParameters): VerifyOtpRequest {
        return VerifyOtpRequest(otp = input.otp, accountNumber = input.accountNumber)
    }
}