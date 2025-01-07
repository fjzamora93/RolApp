package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist.ui.character.CharacterDetailScreen
import com.example.todolist.ui.character.CharacterScreen
import com.example.todolist.ui.character.CharacterViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.CharacterScreen.route // Definimos la pantalla inicial
    ) {
        // Aquí iría la lógica para la pantalla CharacterScreen
        composable(
            ScreensRoutes.CharacterScreen.route
        ) {
            CharacterScreen(
                navController = navController
            )
        }

        // Aquí iría la lógica para la pantalla CharacterDetailScreen
        composable(
            ScreensRoutes.CharacterDetailScreen.route,
        ) {
            CharacterDetailScreen(navController = navController)
        }
    }
}
