package com.example.moneytransfer.ui.screens.more.profile.changePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ChangePasswordParameters
import com.example.domain.model.Status
import com.example.domain.useCase.ChangePasswordUseCase
import com.example.domain.useCase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val chagePasswordUseCase: ChangePasswordUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    private var _changePasswordState = MutableStateFlow<Status<Unit>?>(null)
    val changePasswordState = _changePasswordState.asStateFlow()

    private var _isValidOldPassword = MutableStateFlow(true)
    val isValidOldPassword: StateFlow<Boolean> = _isValidOldPassword

    private var _isValidNewPassword = MutableStateFlow(true)
    val isValidNewPassword: StateFlow<Boolean> = _isValidNewPassword

    fun changePassword(changePasswordParameters: ChangePasswordParameters) {
        viewModelScope.launch {
            chagePasswordUseCase(changePasswordParameters).collect {
                _changePasswordState.value = it
            }
        }
    }

    fun resetChangePasswordState() {
        _changePasswordState.value = null
    }

    fun validateOldPassword(password: String){
        _isValidOldPassword.value = validatePasswordUseCase(password)
    }

    fun validateNewPassword(password: String){
        _isValidNewPassword.value = validatePasswordUseCase(password)
    }

}