package com.example.domain.useCase

import com.example.domain.model.Recipient
import com.example.domain.repository.FavoriteRecipientRepository
import javax.inject.Inject

class AddFavoriteRecipientUscCase @Inject constructor(
    private val favoriteRecipientRepository: FavoriteRecipientRepository
) {
    suspend operator fun invoke(
        recipient: Recipient
    ) = favoriteRecipientRepository.addFavoriteRecipient(recipient)
}