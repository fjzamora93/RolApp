package com.example.todolist.ui.character


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.local.model.RolCharacter

// Importación de otros componentes
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

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
        CharacterDetail()
        StatsForm()
        CharacterList(Modifier.align(Alignment.CenterHorizontally))
    }
}



@Composable
fun StatsForm(){
    val characterViewModel:CharacterViewModel = viewModel()
    var selectedCharacter = characterViewModel.selectedCharacter.value

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var completed by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Checkbox(
            checked = completed,
            onCheckedChange = { completed = it },
        )
        Button(onClick = {val newCharacter = RolCharacter(name = name, description = description, completed = completed)
            characterViewModel.insertCharacter(newCharacter)
        }) {
            Text("Insert")
        }
    }
}

@Composable
fun CharacterDetail(){
    val characterViewModel: CharacterViewModel = viewModel()
    var selectedCharacter = characterViewModel.selectedCharacter.value

    selectedCharacter?.let { character ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Name: ${character.name}")
            Text(text = "Description: ${character.description}")
        }
    } ?: run {
        // Mostrar un mensaje si no hay personaje seleccionado
        Text("No character selected")
    }
}




// LISTA DE PERSONAJES -> NO UTILIZAR DE MOMENTO
@Composable
fun CharacterList(modifier: Modifier) {
    val characterViewModel: CharacterViewModel = viewModel()
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState) // Habilita el scroll
    ) {
        HorizontalDivider(
            Modifier
                .background(Color(0xFFEEEEEE))
                .height(1.dp)
                .fillMaxWidth()
        )

        // Mostrar los personajes
        val characters = characterViewModel.characters.value
        characters?.let {
            it.forEach { character ->
                Text(text = "Name: ${character.name}")
                Text(text = "Description: ${character.description}")
                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    // Botón para eliminar
                    Button(onClick = {
                        characterViewModel.deleteCharacter(character)
                    }) {
                        Text("Delete")
                    }

                    // Botón ver detalles
                    Button(onClick = {
                        characterViewModel.getCharacterById(character.id)
                    }) {
                        Text("Ver detalles")
                    }
                }

            }
        }
    }
}

