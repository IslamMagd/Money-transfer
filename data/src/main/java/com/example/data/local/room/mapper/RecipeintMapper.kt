package com.example.data.local.room.mapper

import com.example.data.local.room.entity.RecipientEntity
import com.example.domain.model.Recipient
import com.example.domain.utill.Mapper

class RecipeintMapper : Mapper<Recipient, RecipientEntity> {
    override fun map(input: Recipient): RecipientEntity {
        return RecipientEntity(
            id = input.id,
            recipientName = input.name,
            recipientCardNumber = input.cardNumber
        )
    }
}