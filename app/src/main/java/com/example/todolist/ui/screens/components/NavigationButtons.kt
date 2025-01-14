package com.example.todolist.ui.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes

@Composable
fun BackButton() {
    val navigationViewModel = LocalNavigationViewModel.current
    val previousRoute = navigationViewModel.routeStack.dropLast(1).lastOrNull()

    Button(onClick = {
        navigationViewModel.goBack()
    }) {
        Text("Back")
    }
}


@Composable
fun CharacterListButton(){
    val navigationViewModel = LocalNavigationViewModel.current
    Button(onClick = {
        navigationViewModel.navigate(ScreensRoutes.CharacterListScreen.route)
    }) {
        Text("Botón-Lista-Personajes")
    }
}

@Composable
fun MainScreenButton(){
    val navigationViewModel = LocalNavigationViewModel.current
    Button(onClick = {
        navigationViewModel.navigate(ScreensRoutes.MainScreen.route)
    }) {
        Text("Botón-Main-Screen")
    }
}

@Composable
fun CharacterDetailButton(idNewCharacter: Int){
    val navigationViewModel = LocalNavigationViewModel.current
    Button(onClick = {
        navigationViewModel.navigate(ScreensRoutes.CharacterDetailScreen.createRoute(idNewCharacter))
    }) {
        Text("Botón-Detalles-Personajes")
    }
}

@Composable
fun CharacterCreatorButton(){
    val navigationViewModel = LocalNavigationViewModel.current
    Button(onClick = {
        navigationViewModel.navigate(ScreensRoutes.CharacterCreatorScreen.route)
    }) {
        Text("Botón-Creación-Personajes")
    }
}
