package com.example.data.remote.mapper.account

import com.example.data.remote.request.AddCardRequest
import com.example.domain.model.AddCardParameters
import com.example.domain.utill.Mapper


class CardParametersMapper : Mapper<AddCardParameters, AddCardRequest> {
    override fun map(input: AddCardParameters): AddCardRequest {
        return AddCardRequest(
            cardNumber = input.cardNumber,
            cardholderName = input.cardholderName,
            expiryDate = input.expiryDate,
            cvv = input.cvv,
            isActive = input.isActive,
            cardCurrency = input.cardCurrency,
            balance = input.balance
        )
    }
}