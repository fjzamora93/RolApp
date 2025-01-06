package com.example.todolist.navigation

sealed class Screens(val route: String) {
    object CharacterScreen : Screens("CharacterScreen")
    object CharacterDetailScreen : Screens("CharacterDetailScreen")

    // TASKS, ANTIGUO
    object TaskListScreen: Screens("TaskListScreen")
    object TaskDetailScreen : Screens("TaskDetailScreen/{taskId}") {
        fun createRoute(taskId: Int) = "TaskDetailScreen/$taskId"
    }
}