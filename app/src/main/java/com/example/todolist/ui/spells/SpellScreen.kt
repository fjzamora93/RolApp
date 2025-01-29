package com.example.todolist.ui.spells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.model.Item
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.layout.MainLayout


@Composable
fun CharacterSpellScreen(){
    MainLayout(){
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            CharacterSpellBody()
            BackButton()
        }
    }
}


@Composable
fun CharacterSpellBody(
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val inventoryItems by characterViewModel.selectedCharacterItems.observeAsState()
    // Si el personaje no está seleccionado, mostramos un texto vacío o de espera
    if (inventoryItems == null) {
        Text("Cargando Hechizos...")
    } else {
        var inventory by remember { mutableStateOf(inventoryItems!!) }
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            inventory.forEach { item ->
                SpellCard(item = item)
            }
        }


    }
}


@Composable
fun SpellCard(item: Item, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Daño: ${item.damageDice}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Precio: ${item.goldValue}",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "vender")
            }
        }
    }
}
