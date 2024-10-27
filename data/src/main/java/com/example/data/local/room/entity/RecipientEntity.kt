package com.example.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipient_table")
data class RecipientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipientName: String,
    val recipientCardNumber: String
)