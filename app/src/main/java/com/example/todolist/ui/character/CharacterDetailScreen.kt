package com.example.todolist.ui.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterDetailScreen(
    characterId : Int,
){
    val navigationViewModel = LocalNavigationViewModel.current
    val characterViewModel: CharacterViewModel = hiltViewModel()
    characterViewModel.getCharacterById(characterId)


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())
        DetailCharacterBody(
            characterViewModel = characterViewModel,
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )
        BackButton()
        Footer(Modifier.fillMaxWidth())
    }
}

@Composable
fun DetailCharacterBody(
    characterViewModel: CharacterViewModel,
    modifier: Modifier = Modifier
) {
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    // Si el personaje no está seleccionado, mostramos un texto vacío o de espera
    if (selectedCharacter == null) {
        Text("Cargando personaje...")
    } else {
        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item {
                // Aquí agregamos los campos del personaje dentro de la columna
                CharacterTextField(label = "Nombre", value = selectedCharacter?.name ?: "")
                CharacterTextField(label = "Descripción", value = selectedCharacter?.description ?: "")
                CharacterTextField(label = "Clase de Rol", value = selectedCharacter?.rolClass?.name ?: "")
                CharacterTextField(label = "Raza", value = selectedCharacter?.race?.name ?: "")
                CharacterTextField(label = "Altura", value = selectedCharacter?.height?.name ?: "")
                CharacterTextField(label = "Peso", value = selectedCharacter?.weight?.name ?: "")
                CharacterTextField(label = "Edad", value = selectedCharacter?.age.toString())

                CharacterNumberField(label = "Fuerza", initialValue = selectedCharacter?.strength ?: 0)
                CharacterNumberField(label = "Destreza", initialValue = selectedCharacter?.dexterity ?: 0)
                CharacterNumberField(label = "Constitución", initialValue = selectedCharacter?.constitution ?: 0)
                CharacterNumberField(label = "Inteligencia", initialValue = selectedCharacter?.intelligence ?: 0)
                CharacterNumberField(label = "Sabiduría", initialValue = selectedCharacter?.wisdom ?: 0)
                CharacterNumberField(label = "Carisma", initialValue = selectedCharacter?.charisma ?: 0)

                CharacterNumberField(label = "Poder", initialValue = selectedCharacter?.power ?: 0)
                CharacterNumberField(label = "Tamaño", initialValue = selectedCharacter?.size ?: 0)
                CharacterNumberField(label = "Cordura", initialValue = selectedCharacter?.sanity ?: 0)
                CharacterNumberField(label = "HP", initialValue = selectedCharacter?.hp ?: 0)
                CharacterNumberField(label = "AP", initialValue = selectedCharacter?.ap ?: 0)
                CharacterNumberField(label = "Nivel", initialValue = selectedCharacter?.level ?: 1)
            }
        }
    }
}

@Composable
fun CharacterTextField(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun CharacterNumberField(
    label: String,
    initialValue: Int,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(initialValue) }

    Column(modifier = modifier.padding(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = modifier.padding(8.dp)
        ){
            IconButton(
                onClick = { value -= 1 }
            ) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrement")
            }

            Text(text = value.toString(), style = MaterialTheme.typography.labelMedium)

            IconButton(
                onClick = { value += 1 }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
            }
        }
    }
}





