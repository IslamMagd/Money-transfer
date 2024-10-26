package com.example.moneytransfer.ui.screens.cards.addCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AddCardParameters
import com.example.domain.model.AddCardResult
import com.example.domain.model.Status
import com.example.domain.useCase.AddCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase
) : ViewModel() {
    private var _addCardState = MutableStateFlow<Status<AddCardResult?>?>(null)
    val addCardState = _addCardState.asStateFlow()

    fun addCard(addCardParameters: AddCardParameters) {
        viewModelScope.launch {
            addCardUseCase(addCardParameters).collect {
                _addCardState.value = it
            }
        }
    }
}