package com.example.moneytransfer.ui.screens.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SignUpResult
import com.example.domain.model.SignupParameters
import com.example.domain.model.Status
import com.example.domain.useCase.LogoutUseCase
import com.example.domain.useCase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private var _logOutState = MutableStateFlow<Status<Unit>?>(null)
    val logOutState = _logOutState.asStateFlow()

    fun logOutUser() {
        viewModelScope.launch {
            logoutUseCase().collect {
                _logOutState.value = it
            }
        }
    }

}