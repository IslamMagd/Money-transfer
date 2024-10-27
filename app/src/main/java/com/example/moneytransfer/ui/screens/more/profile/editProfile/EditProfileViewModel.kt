package com.example.moneytransfer.ui.screens.more.profile.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.EditProfileParameters
import com.example.domain.model.Status
import com.example.domain.useCase.EditProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase
) : ViewModel() {
    private var _editProfileState = MutableStateFlow<Status<Unit>?>(null)
    val editProfileState = _editProfileState.asStateFlow()

    fun editProfile(editProfileParameters: EditProfileParameters) {
        viewModelScope.launch {
            editProfileUseCase(editProfileParameters).collect {
                _editProfileState.value = it
            }
        }
    }

    fun resetEditProfileState() {
        _editProfileState.value = null
    }

}