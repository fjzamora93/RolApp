package com.example.todolist.navigation

sealed class ScreensRoutes(val route: String) {
    object CharacterCreatorScreen : ScreensRoutes("CharacterCreatorScreen")
    object CharacterDetailScreen : ScreensRoutes("CharacterDetailScreen/{characterId}") {
        fun createRoute(characterId: Int) = "CharacterDetailScreen/$characterId"
    }



    // TASKS, ANTIGUO
    object TaskListScreen: ScreensRoutes("TaskListScreen")
    object TaskDetailScreen : ScreensRoutes("TaskDetailScreen/{taskId}") {
        fun createRoute(taskId: Int) = "TaskDetailScreen/$taskId"
    }
}