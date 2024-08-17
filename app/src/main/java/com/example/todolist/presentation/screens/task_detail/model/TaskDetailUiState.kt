package com.example.todolist.presentation.screens.task_detail.model

sealed class TaskDetailUiState {
    data object LOADING : TaskDetailUiState()
    data object SUCCESS : TaskDetailUiState()
    data class ERROR(val message: String) : TaskDetailUiState()
}