package com.example.domain.useCase

import com.example.domain.repository.FavoriteRecipientRepository
import javax.inject.Inject

class GetAllRecipentsUseCase @Inject constructor(
    private val favoriteRecipientRepository: FavoriteRecipientRepository
) {
    operator fun invoke() = favoriteRecipientRepository.getAllFavoriteRecipients()
}