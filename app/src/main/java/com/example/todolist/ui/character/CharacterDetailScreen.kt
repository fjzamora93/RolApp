package com.example.todolist.ui.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterDetailScreen(
    characterId : Int,
){
    val navigationViewModel = LocalNavigationViewModel.current
    println("Character ID recibido: $characterId")  // Verificación
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())
        DetailCharacterBody(
            characterId = characterId,
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )
        Button(onClick = {
            // Método para volver atrás
            navigationViewModel.navigate(ScreensRoutes.CharacterCreatorScreen.route)

        }) {
            Text(text = "Back to Character Screen")
        }

        Footer(Modifier.fillMaxWidth())
    }
}

@Composable
fun DetailCharacterBody(
    characterId: Int,
    modifier:Modifier
){
    Column(modifier = modifier.fillMaxWidth()){
        Text("HOla! ${characterId}")
    }
}


