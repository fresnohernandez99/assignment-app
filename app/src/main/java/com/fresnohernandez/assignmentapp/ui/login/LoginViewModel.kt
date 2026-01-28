package com.fresnohernandez.assignmentapp.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateState(email: String? = null, password: String? = null) {
        email?.let { _uiState.tryEmit(uiState.value.copy(emailInput = it)) }
        password?.let { _uiState.tryEmit(uiState.value.copy(passwordInput = it)) }
    }
}