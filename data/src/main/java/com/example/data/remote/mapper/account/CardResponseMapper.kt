package com.example.data.remote.mapper.account

import com.example.data.remote.response.AddCardResponse
import com.example.domain.model.AddCardResult
import com.example.domain.utill.Mapper

class CardResponseMapper : Mapper<AddCardResponse, AddCardResult> {
    override fun map(input: AddCardResponse): AddCardResult {
        return AddCardResult(
            cardholderName = input.cardholderName,
            cardNumber = input.cardNumber,
            balance = input.balance
        )
    }
}