package com.example.todolist.ui.character.items


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.Item
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.AddButton
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.MenuMedievalButton
import com.example.todolist.ui.screens.components.RegularCard
import com.example.todolist.ui.screens.components.medievalButtonStyleSquare
import com.example.todolist.ui.screens.layout.Footer
import com.example.todolist.ui.screens.layout.Header
import com.example.todolist.ui.screens.layout.MainLayout
import com.example.todolist.util.CustomType

@Composable
fun ItemListScreen(){
    MainLayout(){
        Column(Modifier.fillMaxSize().padding(16.dp)
        ){
            ItemListBody()
            BackButton()
        }
    }
}



// LISTA DE PERSONAJES -> NO UTILIZAR DE MOMENTO
@Composable
fun ItemListBody(
    itemViewModel: ItemViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()

    itemViewModel.getItems(
        name = "",
        onSuccess = { },
        onError = { }
    )

    val items by itemViewModel.itemList.observeAsState()

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (selectedCharacter != null)  {
            Text(
                text = "Oro disponible: ${selectedCharacter?.gold}",
                style = CustomType.bodyMedium
            )
        }


        items?.let {
            it.forEach { item ->
                ItemSummary(item)
            }
        }
    }
}

@Composable
fun ItemSummary(
    item: Item,
    itemViewModel: ItemViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current
) {

    RegularCard() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (characterViewModel.selectedCharacter.value != null) {

                // Botón de comprar
                MenuMedievalButton(
                    onClick = {
                        if (characterViewModel.selectedCharacter.value!!.gold > item.goldValue) {
                            characterViewModel.updateCharacterGold(
                                characterViewModel.selectedCharacter.value!!.gold - item.goldValue
                            )
                            itemViewModel.addItemToCharacter(
                                currentCharacter = characterViewModel.selectedCharacter.value!!,
                                currentItem = item
                            )
                        }

                    },
                    modifier = medievalButtonStyleSquare(size = 50.dp),
                    icon = Icons.Default.MonetizationOn,
                    text = "Precio: ${item.goldValue}"
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ){
                Text(
                    text = item.name,
                    style = CustomType.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Daño: ${item.damageDice}",
                    style = CustomType.bodyMedium
                )
            }

        }
    }
}


