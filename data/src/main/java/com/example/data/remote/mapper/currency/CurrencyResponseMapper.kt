package com.example.data.remote.mapper.currency

import com.example.data.remote.response.ConvertCurrencyResponse
import com.example.domain.model.CurrencyConversionResult
import com.example.domain.utill.Mapper

class CurrencyResponseMapper: Mapper<ConvertCurrencyResponse, CurrencyConversionResult> {
    override fun map(input: ConvertCurrencyResponse): CurrencyConversionResult {
        return CurrencyConversionResult(rate = input.rate, convertedAmount = input.convertedAmount)
    }
}