package com.example.todolist.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.Body
import com.example.todolist.ui.character.CharacterCreatorForm
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun MainScreen(){
    LaunchedEffect(Unit) {
        // Esto se ejecuta al montar el Composable
        Log.d("CharacterScreen", "NavController está listo")
    }
    val navigationViewModel = LocalNavigationViewModel.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())

        Button(onClick = {

            navigationViewModel.navigate(ScreensRoutes.CharacterCreatorScreen.route)
        }) {
            Text("Crear nuevo personaje")
        }

        Footer(Modifier.fillMaxWidth())
    }
}



