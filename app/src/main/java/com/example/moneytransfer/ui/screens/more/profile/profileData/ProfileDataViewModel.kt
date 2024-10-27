package com.example.moneytransfer.ui.screens.more.profile.profileData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Profile
import com.example.domain.model.Status
import com.example.domain.useCase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDataViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
): ViewModel() {
    private var _profileState = MutableStateFlow<Status<Profile?>?>(null)
    val profileState = _profileState.asStateFlow()

    fun getProfile() {
        viewModelScope.launch {
            getProfileUseCase().collect {
                _profileState.value = it
            }
        }
    }
}