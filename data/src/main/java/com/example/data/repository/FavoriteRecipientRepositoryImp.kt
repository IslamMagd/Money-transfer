package com.example.data.repository

import com.example.data.local.room.FavoriteRecipientDao
import com.example.data.local.room.mapper.RecipeintEntityMapper
import com.example.data.local.room.mapper.RecipeintMapper
import com.example.domain.model.Recipient
import com.example.domain.repository.FavoriteRecipientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRecipientRepositoryImp @Inject constructor(
    private val favoriteRecipientDao: FavoriteRecipientDao
): FavoriteRecipientRepository {
    override suspend fun addFavoriteRecipient(recipient: Recipient) {
        favoriteRecipientDao.addFavoriteRecipient(RecipeintMapper().map(recipient))
    }

    override fun getAllFavoriteRecipients(): Flow<List<Recipient>> {
        return favoriteRecipientDao.getAllFavoriteRecipients()
            .map { recipientEntities ->
                RecipeintEntityMapper().mapList(recipientEntities)
            }
    }

    override suspend fun deleteFavoriteRecipient(recipient: Recipient) {
        favoriteRecipientDao.deleteFavoriteRecipient(RecipeintMapper().map(recipient))
    }
}