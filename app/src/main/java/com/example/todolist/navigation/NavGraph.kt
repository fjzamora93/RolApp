package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.ui.character.characterDetail.CharacterDetailScreen
import com.example.todolist.ui.character.CharacterCreatorScreen
import com.example.todolist.ui.character.CharacterListScreen
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.items.ItemListScreen
import com.example.todolist.ui.main.MainScreen
import com.example.todolist.ui.screens.layout.FontsTemplateScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    // Proveer CONSTANTES en el árbol de composables dentro de NavGraph (la navegación, el usuario, un carrito de la compra... lo que va a ser común)
    val navigationViewModel: NavigationViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val characterViewModel: CharacterViewModel = hiltViewModel()

    CompositionLocalProvider(
        LocalNavigationViewModel provides navigationViewModel,
        LocalCharacterViewModel provides characterViewModel
    ) {
        // Declaramos el objeto que va a ser observado
        val navigationEvent by navigationViewModel.navigationEvent.observeAsState()

        // Este LaunchedEffect es un OBSERVADOR que se disparará ante cualquier evento de navegación
        LaunchedEffect(navigationEvent) {
            //println("Navegando a la ruta: $navigationEvent")
            navigationEvent?.let { event ->
                when (event) {
                    is NavigationEvent.Navigate -> {
                        navController.navigate(event.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    is NavigationEvent.NavigateAndPopUp -> {
                        navController.navigate(event.route) {
                            popUpTo(event.popUpToRoute) {
                                inclusive = event.inclusive
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
                navigationViewModel.clearNavigationEvent()
            }
        }


        // Equivalente a un enrutador, compuesto por un CONTROLLER + Ruta de navegación
        NavHost(
            navController = navController,
            startDestination = ScreensRoutes.MainScreen.route
        ) {

            // Pantalla de inicio
            composable(ScreensRoutes.MainScreen.route) {
                MainScreen()
            }

            // Pantalla de creación del personaje
            composable(ScreensRoutes.CharacterCreatorScreen.route) {
                CharacterCreatorScreen()
            }

            composable(ScreensRoutes.CharacterListScreen.route) {
                CharacterListScreen()
            }

            composable(ScreensRoutes.ItemListScreen.route) {
                ItemListScreen()
            }

            composable(ScreensRoutes.FontTemplateScreen.route) {
                FontsTemplateScreen()
            }

            // Pantalla de detalle del personaje
            composable(
                ScreensRoutes.CharacterDetailScreen.route,
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
                CharacterDetailScreen(characterId = characterId)
            }
        }
    }
}
