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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.util.CustomType

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
    if (selectedCharacter != null) {
        var editableCharacter by remember { mutableStateOf(selectedCharacter!!) }
        println("El personaje es EN EL HEADER: ${editableCharacter?.name}")
    }


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

        // Personaje seleccionado
        Text(
            text = " ${selectedCharacter?.name ?: "Ninguno"}",
            style = CustomType.bodyMedium
        )

        // Cerrar la aplicación
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close app",
            modifier = Modifier
                .clickable { activity.finish() } // Acción al hacer clic
        )
    }
}
