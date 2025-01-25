package com.example.todolist.ui.items


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
import com.example.todolist.ui.screens.components.RegularCard
import com.example.todolist.ui.screens.layout.Footer
import com.example.todolist.ui.screens.layout.Header
import com.example.todolist.ui.screens.layout.MainLayout
import com.example.todolist.util.CustomType

@Composable
fun ItemListScreen(){
    MainLayout(){
        Column(Modifier.fillMaxSize().padding(16.dp)
        ){
            BackButton()
            ItemListBody()
        }
    }
}



// LISTA DE PERSONAJES -> NO UTILIZAR DE MOMENTO
@Composable
fun ItemListBody(
    itemViewModel: ItemViewModel = hiltViewModel(),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    itemViewModel.getItems(
        name = "",
        onSuccess = { },
        onError = { }
    )

    val items by itemViewModel.itemList.observeAsState()

    Column(){
        Text("Lista de Objetos: ")
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAF3E0)
        )
    ) {

        RegularCard(){
            Row(){
                if (characterViewModel.selectedCharacter.value != null){
                    AddButton( onClick = {
                        println("Añadiendo desde la screen")
                        itemViewModel.addItemToCharacter(
                            currentCharacter = characterViewModel.selectedCharacter.value!!,
                            currentItem = item
                        )
                    })
                }


                Text(
                    text = "Name: ${item.name}",
                    style = CustomType.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Range: ${item.range}",
                style = CustomType.bodyMedium
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Damage Dice: ${item.damageDice}",
                style = CustomType.bodyMedium
            )
        }

    }
}


