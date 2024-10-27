package com.example.data.remote.mapper.account

import com.example.data.remote.request.TransferMoneyRequst
import com.example.domain.model.TransferMoneyParameters
import com.example.domain.utill.Mapper

class TransferParametersMapper : Mapper<TransferMoneyParameters, TransferMoneyRequst> {
    override fun map(input: TransferMoneyParameters): TransferMoneyRequst {
        return TransferMoneyRequst(
            fromCardNumber = input.fromCardNumber,
            toCardNumber = input.toCardNumber,
            amount = input.amount
        )
    }
}