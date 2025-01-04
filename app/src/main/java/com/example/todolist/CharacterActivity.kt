package com.example.todolist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.ui.character.CharacterScreen
import com.example.todolist.ui.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint  // Asegúrate de usar Hilt para la inyección de dependencias en la Activity
class CharacterActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        println("Inicializando la actividad")

        super.onCreate(savedInstanceState)
        setContent {
            // Aquí se carga el Composable
            CharacterScreen(characterViewModel = characterViewModel)
        }
    }
}