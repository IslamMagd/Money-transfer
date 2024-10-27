package com.example.data.remote.mapper.profile

import com.example.data.remote.request.ChangePasswordRequest
import com.example.domain.model.ChangePasswordParameters
import com.example.domain.utill.Mapper

class ResetPasswordParamsMapper : Mapper<ChangePasswordParameters, ChangePasswordRequest> {
    override fun map(input: ChangePasswordParameters): ChangePasswordRequest {
        return ChangePasswordRequest(
            currentPassword = input.currentPassword,
            newPassword = input.newPassword
        )
    }
}