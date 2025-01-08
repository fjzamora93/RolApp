package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.ui.character.CharacterDetailScreen
import com.example.todolist.ui.character.CharacterCreatorScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.CharacterCreatorScreen.route // Definimos la pantalla inicial
    ) {
        // Aquí iría la lógica para la pantalla CharacterScreen
        composable(
            ScreensRoutes.CharacterCreatorScreen.route
        ) {
            CharacterCreatorScreen(
                navController = navController
            )
        }

        // Aquí iría la lógica para la pantalla CharacterDetailScreen
        composable(
            ScreensRoutes.CharacterDetailScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            var characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailScreen(
                characterId = characterId,
                navController = navController
            )

        }


    }
}
