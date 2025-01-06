package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.character.CharacterScreen
import com.example.todolist.ui.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aquí se carga el Composable
            navHostController = rememberNavController()


            // TODO: AQUÍ LA NAVEGACIÓN COMIENZA CON EL CHARACTER SCREEN, PERO DEBERÍAN UTILIZARSE LOS NavGraphs para empezar a hacer la navegación

            // Composable con navegación y ViewModel
            CharacterScreen(
                navController = navHostController,
                characterViewModel = characterViewModel
            )
        }
    }
}



