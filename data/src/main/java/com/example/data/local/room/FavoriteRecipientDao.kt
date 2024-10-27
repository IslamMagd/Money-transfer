package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.room.entity.RecipientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteRecipient(recipient: RecipientEntity)

    @Query("select * from recipient_table")
    fun getAllFavoriteRecipients(): Flow<List<RecipientEntity>>

    @Delete
    suspend fun deleteFavoriteRecipient(recipient: RecipientEntity)
}