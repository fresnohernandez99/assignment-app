package com.fresnohernandez.assignmentapp.ui.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val emailInput: String = "",
    val passwordInput: String = ""
)