package com.example.todolist.ui.character.characterDetail

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel


@Composable
fun CharacterPortrait(
    context: Context,
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    navigationViewModel: NavigationViewModel = LocalNavigationViewModel.current,
    character: RolCharacter
) {
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()
    val imagePath = "file:///android_asset/portraits/bardo/humano-bardo-1.png"
    AsyncImage(
        model = imagePath,
        contentDescription = "Título no disponible",
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable(onClick = {  navigationViewModel.navigate(ScreensRoutes.CharacterDetailScreen.createRoute(character.id)) })
    )

}
