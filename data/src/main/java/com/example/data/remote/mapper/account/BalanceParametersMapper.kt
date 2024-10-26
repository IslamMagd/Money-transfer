package com.example.data.remote.mapper.account

import com.example.data.remote.request.BalanceRequset
import com.example.domain.model.BalanceParameters
import com.example.domain.utill.Mapper

class BalanceParametersMapper: Mapper<BalanceParameters, BalanceRequset> {
    override fun map(input: BalanceParameters): BalanceRequset {
        return BalanceRequset(cardNumber = input.cardNumber)
    }
}