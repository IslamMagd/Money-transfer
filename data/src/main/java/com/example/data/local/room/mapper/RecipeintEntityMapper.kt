package com.example.data.local.room.mapper

import com.example.data.local.room.entity.RecipientEntity
import com.example.domain.model.Recipient
import com.example.domain.utill.Mapper

class RecipeintEntityMapper : Mapper<RecipientEntity, Recipient> {
    override fun map(input: RecipientEntity): Recipient {
        return Recipient(
            id = input.id,
            name = input.recipientName,
            cardNumber = input.recipientCardNumber
        )
    }

    fun mapList(inputList: List<RecipientEntity>): List<Recipient> {
        return inputList.map { map(it) }
    }
}