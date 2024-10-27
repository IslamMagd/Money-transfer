package com.example.moneytransfer.ui.screens.more.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Recipient
import com.example.domain.useCase.DeleteRecipentuseCase
import com.example.domain.useCase.GetAllRecipentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllRecipentsUseCase: GetAllRecipentsUseCase,
    private val deleteRecipentuseCase: DeleteRecipentuseCase
) : ViewModel() {

    private val _favoriteRecipients = MutableStateFlow<List<Recipient>>(emptyList())
    val favoriteRecipients: StateFlow<List<Recipient>> = _favoriteRecipients

    fun getAllFavoriteRecipents() {
        viewModelScope.launch {
            getAllRecipentsUseCase().collect{ recipients ->
                _favoriteRecipients.value = recipients
            }
        }
    }

    fun deleteFavoriteRecipent(recipient: Recipient) {
        viewModelScope.launch {
            deleteRecipentuseCase(recipient)
        }
    }


}