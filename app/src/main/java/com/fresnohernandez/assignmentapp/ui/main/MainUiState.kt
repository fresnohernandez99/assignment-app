package com.fresnohernandez.assignmentapp.ui.main

data class MainUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)