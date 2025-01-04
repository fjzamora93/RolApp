package com.example.todolist.presentation.character


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.local.model.RolCharacter

// Importación de otros componentes
import com.example.todolist.presentation.screens.components.Footer
import com.example.todolist.presentation.screens.components.Header

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = viewModel()  // Usamos `viewModel()` para obtener el ViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())
        Body(
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )
        Footer(Modifier.fillMaxWidth())
    }
}


@Composable
fun Body(modifier:Modifier){
    Column(modifier = modifier.fillMaxWidth()){
        CharacterList(Modifier.align(Alignment.CenterHorizontally))
    }
}


@Composable
fun CharacterList(modifier: Modifier){
    val characterViewModel: CharacterViewModel = viewModel()
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState) // Habilita el scroll
    ) {
        // Mostrar los personajes
        val characters = characterViewModel.characters.value
        characters?.let {
            it.forEach { character ->
                Text(text = "Name: ${character.name}")
                Text(text = "Description: ${character.description}")
                Text(text = "Completed: ${character.completed}")
                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos
            }
        }

        // Botón para insertar un nuevo personaje
        Button(onClick = {
            println("Insertando un nuevo personaje que se suma al listado ${characterViewModel.characters.value}")
            val newCharacter = RolCharacter(name = "Superman", description = "Superhéroe", completed = true)
            characterViewModel.insertCharacter(newCharacter)
        }) {
            Text(text = "Insert Character")
        }
    }

}
