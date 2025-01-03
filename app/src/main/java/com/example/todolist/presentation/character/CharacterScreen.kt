package com.example.todolist.presentation.character


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.local.model.RolCharacter

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = viewModel()  // Usamos `viewModel()` para obtener el ViewModel
) {
    // Coloca la UI aquí, como una lista de personajes o un formulario
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        // Mostrar los personajes
        val characters = characterViewModel.characters.value
        characters?.let {
            it.forEach { character ->
                Text(text = "Name: ${character.name}")
                Text(text = "Description: ${character.description}")
                Text(text = "Completed: ${character.completed}")
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