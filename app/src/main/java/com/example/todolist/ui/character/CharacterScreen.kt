package com.example.todolist.ui.character


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.Race
import com.example.todolist.data.local.model.Range
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.RolClass
import com.example.todolist.navigation.Screens

// Importación de otros componentes
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = viewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        // Esto se ejecuta al montar el Composable
        Log.d("CharacterScreen", "NavController está listo")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Header(Modifier.fillMaxWidth())
        Body(
            Modifier
                .fillMaxWidth()
                .weight(1f) // ocupar todo el espacio disponible
        )

        CharacterCreatorForm()
        Button(onClick = {
            // Verifica si el NavController no es null antes de navegar
            if (navController.currentDestination != null) {
                navController.navigate(Screens.CharacterDetailScreen.route)
            } else {
                Log.e("NavigationError", "NavController no está listo para navegar.")
            }
        }) {
            Text("Ir a otro destino")
        }


        }

        Footer(Modifier.fillMaxWidth())
    }



@Composable
fun Body(modifier:Modifier){
    Column(modifier = modifier.fillMaxWidth()){
        CharacterDetailSample()
        //CharacterCreatorForm()

        CharacterList(Modifier.align(Alignment.CenterHorizontally))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorForm(){
    val characterViewModel: CharacterViewModel = viewModel()
    var selectedCharacter by remember { mutableStateOf(characterViewModel.selectedCharacter.value) }

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var rolClass by remember { mutableStateOf(RolClass.NINGUNA) }
    var race by remember { mutableStateOf(Race.HUMANO) }
    var height by remember { mutableStateOf(Range.MEDIO) }
    var weight by remember { mutableStateOf(Range.MEDIO) }
    var age by remember { mutableStateOf(18) }
    var selectedItems by remember { mutableStateOf<List<Item>>(emptyList()) }
    var selectedWeight by remember { mutableStateOf(Range.BAJO) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Mensaje de alerta
        Text("Los datos introducidos se utilizará para calcular automáticamente tus stats (fuerza, inteligencia, tamaño, etc.  " +
                "Más adelante podrás modificarlos manualmente" +
                "Cualquier información adicional, siempre puedes incluirla en la DESCRIPCIÓN." )


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
            RolCharacter(
                name = name,
                description = description,
                rolClass = rolClass,
                race = race,
                height = height,
                weight = weight,
                age = age
            ),
            characterViewModel)
    }
}


@Composable
fun InsertCharacterButton(
    newCharacter: RolCharacter,
    characterViewModel : CharacterViewModel,
){
    Button(onClick = {
        characterViewModel.insertCharacter(newCharacter)
        var characterId = newCharacter.id
    }) {
        Text("Insert Character")
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
fun CharacterDetailSample(){
    val characterViewModel: CharacterViewModel = viewModel()
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




// LISTA DE PERSONAJES -> NO UTILIZAR DE MOMENTO
@Composable
fun CharacterList(modifier: Modifier) {
    val characterViewModel: CharacterViewModel = viewModel()
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState) // Habilita el scroll
    ) {
        HorizontalDivider(
            Modifier
                .background(Color(0xFFEEEEEE))
                .height(1.dp)
                .fillMaxWidth()
        )

        // Mostrar los personajes
        val characters = characterViewModel.characters.value
        characters?.let {
            it.forEach { character ->
                Text(text = "Name: ${character.name}")
                Text(text = "Description: ${character.description}")
                Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre elementos

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    // Botón para eliminar
                    Button(onClick = {
                        characterViewModel.deleteCharacter(character)
                    }) {
                        Text("Delete")
                    }

                    // Botón ver detalles
                    Button(onClick = {
                        characterViewModel.getCharacterById(character.id)
                    }) {
                        Text("Ver detalles")
                    }
                }

            }
        }
    }
}

