package com.example.todolist.ui.character

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.CharacterDetailButton
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterListScreen(

){
    val navigationViewModel = LocalNavigationViewModel.current
    val characterViewModel: CharacterViewModel = hiltViewModel()


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(
            onClickMenu = {
                navigationViewModel.navigate(ScreensRoutes.MainScreen.route)
            }
        )
        Text("Lista de personajes: ")

        CharacterListBody(
            characterViewModel = characterViewModel,
            Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        BackButton()

        Footer(Modifier.fillMaxWidth())
    }
}



@Composable
fun CharacterListBody(
    characterViewModel: CharacterViewModel,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()
    val navigationViewModel = LocalNavigationViewModel.current

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
                    CharacterDetailButton(idNewCharacter = character.id)
                    }
                }

            }
        }
    }


