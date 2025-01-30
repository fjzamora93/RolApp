package com.example.todolist.navigation

sealed class ScreensRoutes(val route: String) {
    object MainScreen : ScreensRoutes("MainScreen")
    object CharacterCreatorScreen : ScreensRoutes("CharacterCreatorScreen")
    object CharacterListScreen : ScreensRoutes("CharacterListScreen")
    object CharacterSpellScreen : ScreensRoutes("CharacterSpellScreen")
    object CharacterDetailScreen : ScreensRoutes("CharacterDetailScreen/{characterId}") {
        fun createRoute(characterId: Int) = "CharacterDetailScreen/$characterId"
    }
    object SkillListScreen : ScreensRoutes("SkillListScreen")

    // TEST Y TEMPLATES
    object FontTemplateScreen : ScreensRoutes("FontTemplateScreen")


    // ITEMS
    object ItemListScreen: ScreensRoutes("ItemListScreen")
    object InventoryScreen: ScreensRoutes("InventoryScreen")
    object ItemDetailScreen : ScreensRoutes("ItemDetailScreen/{itemId}") {
        fun createRoute(itemId: Int) = "ItemDetailScreen/$itemId"
    }
}