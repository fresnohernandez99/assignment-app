package com.fresnohernandez.assignmentapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateState(email: String? = null, password: String? = null, isLoading: Boolean? = null) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            emailInput = email ?: currentState.emailInput,
            passwordInput = password ?: currentState.passwordInput,
            isLoading = isLoading ?: currentState.isLoading
        )
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            updateState(isLoading = true)
            delay(1500)
            updateState(isLoading = false)
            onSuccess()
        }
    }
}