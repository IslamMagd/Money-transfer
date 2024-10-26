package com.example.moneytransfer.ui.screens.cards.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Status
import com.example.domain.model.VerifyOtpParameters
import com.example.domain.useCase.SaveCardInfoUseCase
import com.example.domain.useCase.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val verifyOtpUseCase: VerifyOtpUseCase
) : ViewModel() {
    private var _verifyOtpState = MutableStateFlow<Status<Unit>?>(null)
    val verifyOtpState = _verifyOtpState.asStateFlow()

    fun verifyOtp(verifyOtpParameters: VerifyOtpParameters) {
        viewModelScope.launch {
            verifyOtpUseCase(verifyOtpParameters).collect {
                _verifyOtpState.value = it
            }
        }
    }
}