package com.example.domain.useCase

import com.example.domain.model.EditProfileParameters
import com.example.domain.repository.ProfileRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        editProfileParameters: EditProfileParameters
    ) = profileRepository.editProfile(editProfileParameters)

}