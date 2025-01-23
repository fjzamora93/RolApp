package com.example.todolist.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.screens.components.CharacterCreatorButton
import com.example.todolist.ui.screens.components.CharacterListButton
import com.example.todolist.ui.screens.components.ItemListButton
import com.example.todolist.ui.screens.layout.MainLayout

@Composable
fun MainScreen(){
    LaunchedEffect(Unit) {
        // Esto se ejecuta al montar el Composable
        Log.d("CharacterScreen", "NavController está listo")
    }
    MainLayout(){
        Column(
            Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            MainScreenBody(Modifier.fillMaxHeight())
        }
    }
}


@Composable
fun MainScreenBody(modifier: Modifier = Modifier){
    Spacer(modifier = Modifier.height(26.dp))
    CharacterCreatorButton()
    CharacterListButton()
    ItemListButton()
    Spacer(modifier = Modifier.height(26.dp))

}


