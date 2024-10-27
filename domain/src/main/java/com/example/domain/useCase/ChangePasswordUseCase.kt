package com.example.domain.useCase

import com.example.domain.model.ChangePasswordParameters
import com.example.domain.repository.ProfileRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(
        changePasswordParameters: ChangePasswordParameters
    ) = profileRepository.changePassword(changePasswordParameters)

}