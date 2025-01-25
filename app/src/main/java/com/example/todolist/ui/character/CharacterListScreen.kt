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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.CharacterDetailButton
import com.example.todolist.ui.screens.components.MedievalDivider
import com.example.todolist.ui.screens.components.RegularCard
import com.example.todolist.ui.screens.layout.Footer
import com.example.todolist.ui.screens.layout.Header
import com.example.todolist.ui.screens.layout.MainLayout
import com.example.todolist.util.CustomType

@Composable
fun CharacterListScreen(

){
    MainLayout(){
        Text("Lista de personajes: ")
        CharacterListBody()
        BackButton()
    }
}




@Composable
fun CharacterListBody(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val characters = characterViewModel.characters.value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        characters?.let {
            it.forEach { character ->
                CharacterSummary(character)
                }
            }
        }
    }

@Composable
fun CharacterSummary(
    character: RolCharacter,
    characterViewModel: CharacterViewModel = hiltViewModel(),

    ){
    RegularCard(){
        Text(
            text = "Name: ${character.name}",
            style = CustomType.titleMedium

        )

        Text(
            text = "Description: ${character.description}",
            style = CustomType.bodyMedium
        )

        MedievalDivider()

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

