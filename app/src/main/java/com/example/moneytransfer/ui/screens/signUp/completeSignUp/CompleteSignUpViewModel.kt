package com.example.moneytransfer.ui.screens.signUp.completeSignUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SignUpResult
import com.example.domain.model.SignupParameters
import com.example.domain.model.Status
import com.example.domain.useCase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompleteSignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    private var _signupResult = MutableStateFlow<Status<SignUpResult?>?>(null)
    val signupResult = _signupResult.asStateFlow()

    fun signupUser(signupParameters: SignupParameters) {
        viewModelScope.launch {
            registerUserUseCase(signupParameters).collect {
                _signupResult.value = it
            }
        }
    }

    fun resetSignupState() {
        _signupResult.value = null
    }

}