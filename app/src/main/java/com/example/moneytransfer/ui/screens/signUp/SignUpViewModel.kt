package com.example.moneytransfer.ui.screens.signUp

import androidx.lifecycle.ViewModel
import com.example.domain.useCase.ValidateEmailUseCase
import com.example.domain.useCase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {
    private val _isValidEmail = MutableStateFlow(true)
    val isValidEmail: StateFlow<Boolean> = _isValidEmail

    private val _isValidPassword = MutableStateFlow(true)
    val isValidPassword: StateFlow<Boolean> = _isValidPassword

    fun validateSignUpEmail(email: String){
        _isValidEmail.value = validateEmailUseCase(email)
    }

    fun validateSignUpPassword(password: String){
        _isValidPassword.value = validatePasswordUseCase(password)
    }

}