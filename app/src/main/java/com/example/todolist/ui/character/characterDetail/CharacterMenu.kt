package com.example.todolist.ui.character.characterDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.di.LocalNavigationViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.MenuMedievalButton


@Composable
fun CharacterMenu(
    navigation: NavigationViewModel = LocalNavigationViewModel.current,
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        // BOTÓN INVENTARIO
        MenuMedievalButton(
            icon = Icons.Default.Backpack,
            text = "Inventario",
            onClick = {
                navigation.navigate(ScreensRoutes.InventoryScreen.route)
            }
        )

        // HECHIZOS
        MenuMedievalButton(
            icon = Icons.AutoMirrored.Filled.MenuBook,
            text = "Hechizos",
            onClick = {
                navigation.navigate(ScreensRoutes.CharacterSpellScreen.route)
            }
        )

        // HABILIDADES
        MenuMedievalButton(
            icon = Icons.Default.Brush,
            text = "Habilidades",
            onClick = {
                navigation.navigate(ScreensRoutes.InventoryScreen.route)
            }
        )




    }
}


