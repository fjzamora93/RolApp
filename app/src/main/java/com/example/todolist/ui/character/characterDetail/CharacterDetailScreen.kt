package com.example.todolist.ui.character.characterDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.character.items.ItemListBody
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.layout.Header
import com.example.todolist.ui.screens.layout.MainLayout
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun CharacterDetailScreen(
    characterId : Int,
    characterViewModel: CharacterViewModel  = LocalCharacterViewModel.current,
){

    characterViewModel.getCharacterById(characterId)

    MainLayout(){
        Column(Modifier.fillMaxSize().padding(16.dp)
        ){
            DetailCharacterBody()
            BackButton()
        }
    }
}


@Composable
fun DetailCharacterBody(
    characterViewModel: CharacterViewModel   = LocalCharacterViewModel.current,
    navigation: NavigationViewModel = LocalNavigationViewModel.current,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    // Si el personaje no está seleccionado, mostramos un texto vacío o de espera
    if (selectedCharacter == null) {
        Text("Cargando personaje...")
    } else {
        // Mantén un solo estado compartido
        var editableCharacter by remember { mutableStateOf(selectedCharacter!!) }

        CharacterMenu()


        // CAMPOS DE TEXTO
        InfoSection(
            editableCharacter = editableCharacter,
            onCharacterChange = {
                editableCharacter = it
                characterViewModel.updateCharacter(it)
            }
        )

        // CAMPOS NUMÉRICOS Y STATS
        StatSection(
            editableCharacter = editableCharacter,
            onCharacterChange = {
                editableCharacter = it
                characterViewModel.updateCharacter(it)
            }
        )

    }
}
