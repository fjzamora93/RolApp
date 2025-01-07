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
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterDetailScreen(
    navController: NavHostController
){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())
        DetailCharacterBody(
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )
        Button(onClick = {
            // Si quieres navegar hacia atrás, usas navController.popBackStack()
            navController.popBackStack()
        }) {
            Text(text = "Back to Character Screen")
        }

        Footer(Modifier.fillMaxWidth())
    }
}

@Composable
fun DetailCharacterBody(modifier:Modifier){
    Column(modifier = modifier.fillMaxWidth()){
        Text("HOla!")
    }
}

