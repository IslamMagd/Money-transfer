package com.example.moneytransfer.ui.screens.transfer.amount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CurrencyConversionResult
import com.example.domain.model.Recipient
import com.example.domain.model.Status
import com.example.domain.useCase.ConvertCurrencyUseCase
import com.example.domain.useCase.GetAllRecipentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmountViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val getAllRecipentsUseCase: GetAllRecipentsUseCase
) : ViewModel() {
    private var _convertCurrencyState = MutableStateFlow<Status<CurrencyConversionResult?>?>(null)
    val convertCurrencyState = _convertCurrencyState.asStateFlow()

    private val _favoriteRecipients = MutableStateFlow<List<Recipient>>(emptyList())
    val favoriteRecipients: StateFlow<List<Recipient>> = _favoriteRecipients

    fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: Double
    ) {
        viewModelScope.launch {
            convertCurrencyUseCase(fromCurrency, toCurrency, amount).collect {
                _convertCurrencyState.value = it
            }
        }
    }

    fun getAllFavoriteRecipents() {
        viewModelScope.launch {
            getAllRecipentsUseCase().collect{ recipients ->
                _favoriteRecipients.value = recipients
            }
        }
    }

}