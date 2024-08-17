package com.example.todolist.presentation.screens.task_list.model

sealed class TaskListUiState {
    data object LOADING: TaskListUiState()
    data object SUCCESS: TaskListUiState()
    data object EMPTY: TaskListUiState()
}