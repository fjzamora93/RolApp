package com.example.todolist.ui.character


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.Race
import com.example.todolist.data.local.model.Range
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.RolClass
import com.example.todolist.navigation.LocalNavigationViewModel
import com.example.todolist.navigation.NavigationViewModel
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.components.BackButton

// Importación de otros componentes
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterCreatorScreen(

) {
    val characterViewModel: CharacterViewModel = hiltViewModel()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(onClickMenu = { })
        Body(
            characterViewModel = characterViewModel,
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )

        Footer(Modifier.fillMaxWidth())
        }
    }


@Composable
fun Body(
    characterViewModel: CharacterViewModel,
    modifier:Modifier
){
    Column(modifier = modifier.fillMaxWidth()){
        CharacterCreatorForm(
            characterViewModel = characterViewModel,
        )

    }
}

@Composable
fun InsertCharacterButton(
    newCharacter: RolCharacter,
    characterViewModel: CharacterViewModel,
) {
    val navigationViewModel = LocalNavigationViewModel.current
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()
    var isNavigating by remember { mutableStateOf(false) }
    BackButton()
    Button(
        onClick = {
            characterViewModel.insertCharacter(newCharacter)
            isNavigating = true
        }
    ) {
        Text("Insertar y navegar")
    }

    // Utiliza LaunchedEffect para esperar a que selectedCharacter se actualice
    LaunchedEffect(isNavigating, selectedCharacter) {
        // Aseguramos que solo se navega cuando isNavigating es true y selectedCharacter tiene un valor válido. SI no lo hacemos, no nos dejará volver atrás.
        if (isNavigating && selectedCharacter?.id != null) {
            selectedCharacter?.id?.let { idNewCharacter ->
                // Ahora que selectedCharacter tiene una id válida, navegamos
                println("ID del último personaje insertado: $idNewCharacter")
                navigationViewModel.navigate(ScreensRoutes.CharacterDetailScreen.createRoute(idNewCharacter))
                isNavigating = false // Después de la navegación, desactivamos el flag
            }
        }
    }
}



@Composable
fun CharacterCreatorForm(
    characterViewModel: CharacterViewModel,
){
    var selectedCharacter by remember { mutableStateOf(characterViewModel.selectedCharacter.value) }

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var rolClass by remember { mutableStateOf(RolClass.NINGUNA) }
    var race by remember { mutableStateOf(Race.HUMANO) }
    var height by remember { mutableStateOf(Range.MEDIO) }
    var weight by remember { mutableStateOf(Range.MEDIO) }
    var age by remember { mutableStateOf(18) }
    var selectedItems by remember { mutableStateOf<List<Item>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Nombre y edad
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                Modifier.weight(3f)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") }
                )
            }
            Column(
                Modifier.weight(1f)
            ) {
                TextField(
                    value = age.toString(),
                    onValueChange = { age = it.toIntOrNull() ?: age },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        // Descripción
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        // Altura y peso
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                Modifier.weight(1f)
            ){
                DropdownSelector(
                    label = "Altura",
                    selectedValue = height,
                    onValueChange = { height = it },
                    values = Range.values(),
                )
            }
            Column(
                Modifier.weight(1f)
            ){
                DropdownSelector(
                    label = "Peso",
                    selectedValue = weight,
                    onValueChange = { weight = it },
                    values = Range.values(),
                )
            }
        }


        // Raza y clase
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                Modifier.weight(1f)
            ){
                DropdownSelector(
                    label = "Clase",
                    selectedValue = rolClass,
                    onValueChange = { rolClass = it },
                    values = RolClass.values(),
                )
            }
            Column(
                Modifier.weight(1f)
            ){
                DropdownSelector(
                    label = "Raza",
                    selectedValue = race,
                    onValueChange = { race = it },
                    values = Race.values(),
                )
            }
        }

        InsertCharacterButton(
            newCharacter = RolCharacter(
                name = name,
                description = description,
                rolClass = rolClass,
                race = race,
                height = height,
                weight = weight,
                age = age
            ),
            characterViewModel = characterViewModel,
        )
    }
}






// DROP DOWN
@Composable
fun <T> DropdownSelector(
    label: String,
    selectedValue: T, // Usamos Range como ejemplo, pero puedes usar cualquier tipo de dato
    onValueChange: (T) -> Unit,
    values: Array<T>, // Array de las opciones a mostrar
) where T : Enum<T> {
    var expanded by remember { mutableStateOf(false) }

    TextField(
        value = selectedValue.name,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        trailingIcon = {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", modifier = Modifier.clickable { expanded = !expanded })
        },
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        values.forEach { selectionOption ->
            DropdownMenuItem(
                onClick = {
                    onValueChange(selectionOption) // Actualiza el valor seleccionado
                    expanded = false
                },
                text = { Text(selectionOption.name) }
            )
        }
    }

}




@Composable
fun CharacterDetailSample(characterViewModel: CharacterViewModel){
    var selectedCharacter = characterViewModel.selectedCharacter.value

    selectedCharacter?.let { character ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Name: ${character.name}")
            Text(text = "Description: ${character.description}")
        }
    } ?: run {
        // Mostrar un mensaje si no hay personaje seleccionado
        Text("No character selected")
    }
}



//
//// LISTA DE PERSONAJES -> NO UTILIZAR DE MOMENTO
//@Composable
//fun CharacterList(
//    characterViewModel: CharacterViewModel,
//    modifier: Modifier
//) {
//    val scrollState = rememberScrollState()
//    val navigationViewModel = LocalNavigationViewModel.current
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .padding(16.dp)
//            .verticalScroll(scrollState) // Habilita el scroll
//    ) {
//
//        // Mostrar los personajes
//        val characters = characterViewModel.characters.value
//        characters?.let {
//            it.forEach { character ->
//                Text(text = "Name: ${character.name}")
//                Text(text = "Description: ${character.description}")
//                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos
//
//                Row(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ){
//                    // Botón para eliminar
//                    Button(onClick = {
//                        characterViewModel.deleteCharacter(character)
//                    }) {
//                        Text("Delete")
//                    }
//
//                    // Botón ver detalles
//                    Button(onClick = {
//                        navigationViewModel.navigate(ScreensRoutes.CharacterDetailScreen.createRoute(character.id))
//                    }) {
//                        Text("Ver detalles")
//                    }
//                }
//
//            }
//        }
//    }
//}
//
