package com.example.todolist.ui.screens.character.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.Item
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.ui.viewmodels.CharacterViewModel
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.RegularCard
import com.example.todolist.ui.screens.layout.MainLayout
import com.example.todolist.ui.viewmodels.ItemViewModel
import com.example.todolist.util.MedievalColours

@Composable
fun CharacterInventoryScreen(){
    MainLayout(){
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            CharacterInventoryBody()
            BackButton()
        }
    }
}


@Composable
fun CharacterInventoryBody(
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val inventoryItems by characterViewModel.selectedCharacterItems.observeAsState()
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    if (inventoryItems == null) {
        Text("Cargando objetos...")
    } else {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CurrentGold()
            inventoryItems!!.forEach { item ->
                InventoryItemCard(item = item)
            }
        }


    }
}


@Composable
fun InventoryItemCard(
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    itemViewModel: ItemViewModel = hiltViewModel(),
    item: Item,
    modifier: Modifier = Modifier
) {
    RegularCard() {
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
            Button(onClick = {
                var currentGold = characterViewModel.selectedCharacter.value?.gold ?: 0
                characterViewModel.updateCharacterGold(item.goldValue + currentGold)
                characterViewModel.removeItemFromCharacter(characterViewModel.selectedCharacter.value!!, item)
            }) {
                Text(text = "vender")
            }
        }
    }
}

@Composable
fun CurrentGold(
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current
){
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()
    var goldText by remember { mutableStateOf(selectedCharacter?.gold?.toString() ?: "0") }
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Icon(imageVector = Icons.Default.MonetizationOn, contentDescription = "", tint = MedievalColours.Gold, modifier = Modifier.size(40.dp))

        TextField(
            value = selectedCharacter!!.gold.toString(),
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    goldText = newValue
                    val newGold = newValue.toIntOrNull() ?: 0
                    characterViewModel.updateCharacterGold(newGold)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Cantidad de oro") }
        )

    }



}
