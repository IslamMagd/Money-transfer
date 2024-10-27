package com.example.domain.repository

import com.example.domain.model.ChangePasswordParameters
import com.example.domain.model.EditProfileParameters
import com.example.domain.model.Profile
import com.example.domain.model.Status
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): Flow<Status<Profile?>>

    suspend fun editProfile(editProfileParameters: EditProfileParameters): Flow<Status<Unit>>


}