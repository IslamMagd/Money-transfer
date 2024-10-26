package com.example.data.remote.mapper.account

import com.example.data.remote.response.BalanceResponse
import com.example.domain.model.Balance
import com.example.domain.utill.Mapper

class BalanceResponseMapper: Mapper<BalanceResponse, Balance> {
    override fun map(input: BalanceResponse): Balance {
        return Balance(balance = input.balance)
    }
}