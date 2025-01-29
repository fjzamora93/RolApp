package com.example.todolist.ui.screens.layout

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.util.CustomType
import com.example.todolist.util.MedievalColours

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onClickMenu : () -> Unit
) {

    HeaderBody( onClickMenu = onClickMenu )
    HorizontalDivider(
        Modifier
            .background(Color(0xFFEEEEEE))
            .height(1.dp)
            .fillMaxWidth())

}


@Composable
fun HeaderBody(
    modifier: Modifier = Modifier,
    onClickMenu : () -> Unit,
    characterViewModel: CharacterViewModel  = LocalCharacterViewModel.current,
){
    val activity = LocalContext.current as Activity
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Menú de la aplicación
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "open menu",
            modifier = Modifier
                .clickable { onClickMenu() }
        )

        CharacterThumbnail()

        // Cerrar la aplicación
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close app",
            modifier = Modifier
                .clickable { activity.finish() } // Acción al hacer clic
        )
    }
}

// MINIATURA DEL PERSONAJE
@Composable
fun CharacterThumbnail(
    navigationViewModel: NavigationViewModel = LocalNavigationViewModel.current,
    characterViewModel: CharacterViewModel  = LocalCharacterViewModel.current,
){
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        IconButton(
            onClick = {
                if (selectedCharacter != null) {
                    navigationViewModel.navigate(ScreensRoutes.CharacterDetailScreen.createRoute(
                        selectedCharacter!!.id))
                } else {
                    navigationViewModel.navigate(ScreensRoutes.CharacterListScreen.route)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.SupervisedUserCircle,
                contentDescription = "Detail",
                modifier = Modifier
                    .size(60.dp)
                    .shadow(4.dp, CircleShape),
                tint = MedievalColours.IronDark
            )
        }
        Text(
            text = " ${selectedCharacter?.name ?: "Ninguno"}",
            style = CustomType.bodyMedium,
        )
    }
}