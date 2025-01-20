package com.example.todolist.navigation

sealed class ScreensRoutes(val route: String) {
    object MainScreen : ScreensRoutes("MainScreen")
    object CharacterCreatorScreen : ScreensRoutes("CharacterCreatorScreen")
    object CharacterListScreen : ScreensRoutes("CharacterListScreen")
    object CharacterDetailScreen : ScreensRoutes("CharacterDetailScreen/{characterId}") {
        fun createRoute(characterId: Int) = "CharacterDetailScreen/$characterId"
    }

    // ITEMS
    object ItemListScreen: ScreensRoutes("ItemListScreen")
    object ItemDetailScreen : ScreensRoutes("ItemDetailScreen/{itemId}") {
        fun createRoute(itemId: Int) = "ItemDetailScreen/$itemId"
    }
}