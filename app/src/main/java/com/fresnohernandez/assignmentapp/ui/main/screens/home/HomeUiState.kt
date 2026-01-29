package com.fresnohernandez.assignmentapp.ui.main.screens.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)