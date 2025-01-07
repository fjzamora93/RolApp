package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolist.navigation.NavGraph
import com.example.todolist.ui.character.CharacterScreen
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.util.DefaultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            val navHostController: NavHostController = rememberNavController()

            // TODO: AQUÍ LA NAVEGACIÓN COMIENZA CON EL CHARACTER SCREEN, PERO DEBERÍAN UTILIZARSE LOS NavGraphs para empezar a hacer la navegación
            DefaultTheme {
                NavGraph(
                    navController = navHostController,
                )
            }

        }
    }
}



