package com.example.todolist.di

import androidx.compose.runtime.compositionLocalOf
import com.example.todolist.ui.navigation.NavigationViewModel
import com.example.todolist.ui.viewmodels.CharacterViewModel

// CONSTANTES DE VIEWMODELS QUE VAN A SER COMPARTIDAS EN TODAS LAS PANTALLAS


val LocalNavigationViewModel = compositionLocalOf<NavigationViewModel> {
    error("NavigationViewModel no está disponible. Asegúrate de proporcionarlo.")
}


val LocalCharacterViewModel = compositionLocalOf<CharacterViewModel> {
    error("LocalCharacterViewModel no está disponible. Asegúrate de proporcionarlo.")
}


// INCLUIR USER CUANDO ESTÉ CREADA LA CLASE CORRESPONDIENTE