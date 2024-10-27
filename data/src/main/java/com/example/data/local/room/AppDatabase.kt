package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.room.entity.RecipientEntity

@Database(entities = [RecipientEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteRecipientDao(): FavoriteRecipientDao
}