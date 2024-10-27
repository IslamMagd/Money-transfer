package com.example.moneytransfer.ui.screens.transfer.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Status
import com.example.domain.model.TransferMoneyParameters
import com.example.domain.useCase.TransferMoneyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val transferMoneyUseCase: TransferMoneyUseCase
) : ViewModel() {
    private var _moneyTransferState = MutableStateFlow<Status<Unit>?>(null)
    val moneyTransferState = _moneyTransferState.asStateFlow()

    fun transferMoney(transferMoneyRequest: TransferMoneyParameters) {
        viewModelScope.launch {
            transferMoneyUseCase(transferMoneyRequest).collect {
                _moneyTransferState.value = it
            }
        }
    }

    fun resetMoneyTransferState() {
        _moneyTransferState.value = null
    }

}