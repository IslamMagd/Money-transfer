package com.example.moneytransfer.ui.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.LoginParameters
import com.example.domain.model.LoginResult
import com.example.domain.model.Status
import com.example.domain.useCase.GetEmailUseCase
import com.example.domain.useCase.GetPasswordUseCase
import com.example.domain.useCase.LoginUseCase
import com.example.domain.useCase.SaveCredentialsUseCase
import com.example.domain.useCase.SaveTokenUseCase
import com.example.domain.useCase.ValidateEmailUseCase
import com.example.domain.useCase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveCredentialsUseCase: SaveCredentialsUseCase,
    private val getEmailUseCase: GetEmailUseCase,
    private val getPasswordUseCase: GetPasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
): ViewModel() {
    private var _loginState = MutableStateFlow<Status<LoginResult?>?>(null)
    val loginState = _loginState.asStateFlow()

    private val _isValidEmail = MutableStateFlow(true)
    val isValidEmail: StateFlow<Boolean> = _isValidEmail

    private val _isValidPassword = MutableStateFlow(true)
    val isValidPassword: StateFlow<Boolean> = _isValidPassword

    fun loginUser(loginParameters: LoginParameters){
        viewModelScope.launch {
            loginUseCase(loginParameters).collect {
                _loginState.value = it
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = null
    }

    fun saveToken(token: String){
        saveTokenUseCase(token)
    }

    fun saveCredentilas(email: String, password: String){
        saveCredentialsUseCase(email, password)
    }

    fun getEmail(): String?{
       return getEmailUseCase()
    }

    fun getPassword(): String?{
        return getPasswordUseCase()
    }

    fun validateSignInEmail(email: String){
        _isValidEmail.value = validateEmailUseCase(email)
    }

    fun validateSignInPassword(password: String){
        _isValidPassword.value = validatePasswordUseCase(password)
    }
}