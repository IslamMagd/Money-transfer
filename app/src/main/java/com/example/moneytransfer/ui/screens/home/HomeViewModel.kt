package com.example.moneytransfer.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Balance
import com.example.domain.model.Status
import com.example.domain.model.TransactionResult
import com.example.domain.useCase.GetBalanceUseCase
import com.example.domain.useCase.GetCardNumberUseCase
import com.example.domain.useCase.GetTransactionsUseCase
import com.example.domain.useCase.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getCardNumberUseCase: GetCardNumberUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {
    private var _balanceState = MutableStateFlow<Status<Balance?>?>(null)
    val balanceState = _balanceState.asStateFlow()

    private var _transactionsState = MutableStateFlow<Status<List<TransactionResult>?>?>(null)
    val transactionsState = _transactionsState.asStateFlow()

    fun getBalance(accountNumber: String) {
        viewModelScope.launch {
            getBalanceUseCase(accountNumber).collect { _balanceState.value = it }
        }
    }

    fun getuserTrnasactions() {
        viewModelScope.launch {
            getTransactionsUseCase().collect {
                _transactionsState.value = it
            }
        }
    }

    fun getCardNumber(): String?{
        return getCardNumberUseCase()
    }

    fun getUserName(): String?{
        return getUserNameUseCase()
    }
}