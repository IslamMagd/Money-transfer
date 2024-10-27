package com.example.data.remote.mapper.profile

import com.example.data.remote.request.EditProfileRequest
import com.example.domain.model.EditProfileParameters
import com.example.domain.utill.Mapper

class EditProfileParamsMapper : Mapper<EditProfileParameters, EditProfileRequest> {
    override fun map(input: EditProfileParameters): EditProfileRequest {
        return EditProfileRequest(
            username = input.username,
            birthdate = input.birthdate,
            country = input.country
        )
    }
}