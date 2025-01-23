package com.example.todolist.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.character.Body
import com.example.todolist.ui.character.CharacterCreatorForm
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.CharacterCreatorButton
import com.example.todolist.ui.screens.components.CharacterListButton
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header
import com.example.todolist.ui.screens.components.ItemListButton
import com.example.todolist.ui.screens.components.LeftHalfDrawer
import kotlinx.coroutines.launch

@Composable
fun MainScreen(){
    LaunchedEffect(Unit) {
        // Esto se ejecuta al montar el Composable
        Log.d("CharacterScreen", "NavController está listo")
    }
    val navigationViewModel = LocalNavigationViewModel.current
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

                MainScreenBody(Modifier.fillMaxHeight())

                Footer(Modifier.fillMaxWidth())
            }
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


