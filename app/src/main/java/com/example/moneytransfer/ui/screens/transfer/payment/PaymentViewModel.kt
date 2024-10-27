package com.example.moneytransfer.ui.screens.transfer.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Recipient
import com.example.domain.useCase.AddFavoriteRecipientUscCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel  @Inject constructor(
    private val addFavoriteRecipientUscCase: AddFavoriteRecipientUscCase
) : ViewModel() {

    fun addFavoriteRecipient(recipient: Recipient) {
        viewModelScope.launch {
            addFavoriteRecipientUscCase(recipient)
        }
    }

}