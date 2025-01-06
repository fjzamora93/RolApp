package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.ui.character.CharacterCreatorForm
import com.example.todolist.ui.character.CharacterDetailScreen
import com.example.todolist.ui.character.CharacterScreen
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.task_detail.TaskDetailScreen
import com.example.todolist.ui.screens.task_list.TaskListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    characterViewModel: CharacterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.CharacterScreen.route // Definimos la pantalla inicial
    ) {
        composable(Screens.CharacterScreen.route) {
            // Aquí iría la lógica para la pantalla CharacterScreen
            CharacterScreen(navController = navController)
        }
        composable(Screens.CharacterDetailScreen.route) {
            // Aquí iría la lógica para la pantalla CharacterDetailScreen
            CharacterDetailScreen(navController = navController)
        }
    }
}
