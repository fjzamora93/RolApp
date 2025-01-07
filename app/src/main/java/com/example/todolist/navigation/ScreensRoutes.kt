package com.example.todolist.navigation

sealed class ScreensRoutes(val route: String) {
    object CharacterScreen : ScreensRoutes("CharacterScreen")
    object CharacterDetailScreen : ScreensRoutes("CharacterDetailScreen")

    // TASKS, ANTIGUO
    object TaskListScreen: ScreensRoutes("TaskListScreen")
    object TaskDetailScreen : ScreensRoutes("TaskDetailScreen/{taskId}") {
        fun createRoute(taskId: Int) = "TaskDetailScreen/$taskId"
    }
}