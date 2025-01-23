package com.example.todolist.ui.screens.layout


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.launch

/**
 * Composable plantilla, disponible para todas las screens, que incluye:
 * - Header.
 * - Menú lateral desplegable.
 * - Footer.
 * */
@Composable
fun MainLayout(
    content: @Composable () -> Unit
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    LeftHalfDrawer(
        drawerState = drawerState,
        onClose = {
            coroutineScope.launch { drawerState.close() }
        }
    ) {
        Box(Modifier.fillMaxSize()){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Header(
                    onClickMenu = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )

                // CONTENIDO DEL SCREEN. MODIFICAR SI FUESE NECESARIO.
                LazyColumn(){
                    item {
                        content()
                        Footer(Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}


