package com.example.todolist.ui.items


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.Item
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.CharacterDetailButton
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun ItemListScreen(

){
    val navigationViewModel = LocalNavigationViewModel.current
    val characterViewModel: CharacterViewModel = hiltViewModel()
    val itemViewModel: ItemViewModel = hiltViewModel()


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(
            onClickMenu = { }
        )
        Text("Lista de Objetos: ")

        Button(
            onClick = {
                itemViewModel.getItems(
                    name = "",
                    onSuccess = { weapons ->
                        println("Armas recibidas: $weapons")
                    },
                    onError = {
                        println("Error al obtener las armas")
                    }
                )
            }
        ) {
            Text("Cargar Objetos")
        }

        ItemListBody()
        BackButton()

        Footer(Modifier.fillMaxWidth())
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

    val scrollState = rememberScrollState()
    val navigationViewModel = LocalNavigationViewModel.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState) // Habilita el scroll
    ) {

        // Mostrar los objetos
        val items by itemViewModel.itemList.observeAsState()

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
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAF3E0) // Fondo crema
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFFAF3E0)) // Fondo crema
        ) {
            Text(
                text = "Name: ${item.name}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8B4513) // Color marrón oscuro
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Range: ${item.range}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF8B4513)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Damage Dice: ${item.damageDice}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF8B4513)
                )
            )

        }
    }
}


