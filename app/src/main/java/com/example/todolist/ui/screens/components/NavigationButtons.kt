package com.example.todolist.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.util.MedievalColours

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
fun BackButton2() {
    val navigationViewModel = LocalNavigationViewModel.current

    IconButton(
        onClick = { navigationViewModel.goBack() },
        modifier = Modifier
            .size(48.dp) // Tamaño del botón
            .clip(CircleShape) // Forma circular
            .background(MedievalColours.Bronze) // Fondo atractivo
            .padding(8.dp) // Espaciado interno
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Flecha hacia atrás
            contentDescription = "Go Back", // Descripción para accesibilidad
            tint = MedievalColours.Gold, // Color del ícono
            modifier = Modifier.size(24.dp) // Tamaño del ícono
        )
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
fun ItemListButton(){
    val navigationViewModel = LocalNavigationViewModel.current
    Button(onClick = {
        navigationViewModel.navigate(ScreensRoutes.ItemListScreen.route)
    }) {
        Text("Botón-Items")
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
