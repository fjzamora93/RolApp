package com.example.todolist.ui.character.characterDetail

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel
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
        // Mantén un solo estado compartido
        var editableCharacter by remember { mutableStateOf(selectedCharacter!!) }

        LazyColumn(modifier = modifier.fillMaxWidth()) {
            item {
                UpdateCharacterButton(editableCharacter)

                // CAMPOS DE TEXTO
                InfoSection(
                    editableCharacter = editableCharacter,
                    onCharacterChange = { editableCharacter = it }
                )

                // CAMPOS NUMÉRICOS Y STATS
                StatSection(
                    editableCharacter = editableCharacter,
                    onCharacterChange = { editableCharacter = it }
                )
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