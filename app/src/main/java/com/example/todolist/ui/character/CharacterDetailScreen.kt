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
        var editableCharacter by remember { mutableStateOf(selectedCharacter!!) }

        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item {
                UpdateCharacterButton(editableCharacter)

                // Aquí agregamos los campos del personaje dentro de la columna
                CharacterTextField(label = "Nombre", value = selectedCharacter?.name ?: "")
                CharacterTextField(label = "Descripción", value = selectedCharacter?.description ?: "")
                CharacterTextField(label = "Clase de Rol", value = selectedCharacter?.rolClass?.name ?: "")
                CharacterTextField(label = "Raza", value = selectedCharacter?.race?.name ?: "")
                CharacterTextField(label = "Altura", value = selectedCharacter?.height?.name ?: "")
                CharacterTextField(label = "Peso", value = selectedCharacter?.weight?.name ?: "")
                CharacterTextField(label = "Edad", value = selectedCharacter?.age.toString())

                CharacterNumberField(
                    label = "Fuerza",
                    value = editableCharacter.strength,
                    onValueChange = { editableCharacter = editableCharacter.copy(strength = it) }
                )
                CharacterNumberField(
                    label = "Destreza",
                    value = editableCharacter.dexterity,
                    onValueChange = { editableCharacter = editableCharacter.copy(dexterity = it) }
                )
                CharacterNumberField(
                    label = "Constitución",
                    value = editableCharacter.constitution,
                    onValueChange = { editableCharacter = editableCharacter.copy(constitution = it) }
                )
                CharacterNumberField(
                    label = "Inteligencia",
                    value = editableCharacter.intelligence,
                    onValueChange = { editableCharacter = editableCharacter.copy(intelligence = it) }
                )
                CharacterNumberField(
                    label = "Sabiduría",
                    value = editableCharacter.wisdom,
                    onValueChange = { editableCharacter = editableCharacter.copy(wisdom = it) }
                )
                CharacterNumberField(
                    label = "Carisma",
                    value = editableCharacter.charisma,
                    onValueChange = { editableCharacter = editableCharacter.copy(charisma = it) }
                )

                CharacterNumberField(
                    label = "Poder",
                    value = editableCharacter.power,
                    onValueChange = { editableCharacter = editableCharacter.copy(power = it) }
                )
                CharacterNumberField(
                    label = "Tamaño",
                    value = editableCharacter.size,
                    onValueChange = { editableCharacter = editableCharacter.copy(size = it) }
                )
                CharacterNumberField(
                    label = "Cordura",
                    value = editableCharacter.sanity,
                    onValueChange = { editableCharacter = editableCharacter.copy(sanity = it) }
                )
                CharacterNumberField(
                    label = "HP",
                    value = editableCharacter.hp,
                    onValueChange = { editableCharacter = editableCharacter.copy(hp = it) }
                )
                CharacterNumberField(
                    label = "AP",
                    value = editableCharacter.ap,
                    onValueChange = { editableCharacter = editableCharacter.copy(ap = it) }
                )
                CharacterNumberField(
                    label = "Nivel",
                    value = editableCharacter.level,
                    onValueChange = { editableCharacter = editableCharacter.copy(level = it) }
                )

            }
        }
    }
}




@Composable
fun CharacterNumberField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var localValue by remember { mutableStateOf(value) }
    Column(modifier = modifier.padding(4.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = {
                localValue -= 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrement")
            }
            Text(text = localValue.toString(), style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = {
                localValue += 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
            }
        }
    }
}

@Composable
fun UpdateCharacterButton(
    updatedCharacter: RolCharacter
){
    val navigationViewModel = LocalNavigationViewModel.current
    val characterViewModel: CharacterViewModel = hiltViewModel()

    Button(
        onClick = {
            println("Personaje a actualizar: ${updatedCharacter}")
            characterViewModel.updateCharacter(updatedCharacter)
            navigationViewModel.navigate(ScreensRoutes.CharacterListScreen.route)
        }
    ){
        Text("Actualizar")
    }
}

@Composable
fun CharacterTextField(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = label, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

