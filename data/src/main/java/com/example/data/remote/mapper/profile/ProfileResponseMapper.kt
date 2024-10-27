package com.example.data.remote.mapper.profile

import com.example.data.remote.response.ProfileResponse
import com.example.domain.model.Profile
import com.example.domain.utill.Mapper

class ProfileResponseMapper : Mapper<ProfileResponse, Profile> {
    override fun map(input: ProfileResponse): Profile {
        return Profile(
            fullname = input.fullname,
            email = input.email,
            dateOfBirth = input.dateOfBirth,
            country = input.country,
            accountNumber = input.accountNumber
        )
    }
}