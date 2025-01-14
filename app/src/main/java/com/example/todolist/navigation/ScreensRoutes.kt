package com.example.todolist.navigation

sealed class ScreensRoutes(val route: String) {
    object MainScreen : ScreensRoutes("MainScreen")
    object CharacterCreatorScreen : ScreensRoutes("CharacterCreatorScreen")
    object CharacterListScreen : ScreensRoutes("CharacterListScreen")
    object CharacterDetailScreen : ScreensRoutes("CharacterDetailScreen/{characterId}") {
        fun createRoute(characterId: Int) = "CharacterDetailScreen/$characterId"
    }





    // TASKS, ANTIGUO
    object TaskListScreen: ScreensRoutes("TaskListScreen")
    object TaskDetailScreen : ScreensRoutes("TaskDetailScreen/{taskId}") {
        fun createRoute(taskId: Int) = "TaskDetailScreen/$taskId"
    }
}