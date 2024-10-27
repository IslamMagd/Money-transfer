package com.example.domain.repository

import com.example.domain.model.Recipient
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipientRepository {
    suspend fun addFavoriteRecipient(recipient: Recipient)

    fun getAllFavoriteRecipients(): Flow<List<Recipient>>

    suspend fun deleteFavoriteRecipient(recipient: Recipient)
}