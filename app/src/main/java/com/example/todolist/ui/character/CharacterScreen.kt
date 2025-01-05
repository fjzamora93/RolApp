package com.example.todolist.ui.character


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
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.Race
import com.example.todolist.data.local.model.Range
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.RolClass

// Importación de otros componentes
import com.example.todolist.ui.screens.components.Footer
import com.example.todolist.ui.screens.components.Header

@Composable
fun CharacterScreen(
    characterViewModel: CharacterViewModel = viewModel()  // Usamos `viewModel()` para obtener el ViewModel
) {
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
        Footer(Modifier.fillMaxWidth())
    }
}


@Composable
fun Body(modifier:Modifier){
    Column(modifier = modifier.fillMaxWidth()){
        CharacterDetail()
        CharacterCreatorForm()
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
    var race by remember { mutableStateOf(Race.NINGUNA) }
    var height by remember { mutableStateOf(Range.MEDIO) }
    var weight by remember { mutableStateOf(Range.MEDIO) }
    var age by remember { mutableStateOf(18) }
    var selectedItems by remember { mutableStateOf<List<Item>>(emptyList()) }
    var selectedWeight by remember { mutableStateOf(Range.BAJO) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Hacer el formulario desplazable
    ) {
        // Nombre
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        // Descripción
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )


        // Altura
        DropdownSelector(
            label = "Height",
            selectedValue = height,
            onValueChange = { height = it },
            values = Range.values()
        )

        // Peso
        DropdownSelector(
            label = "Weight",
            selectedValue = weight,
            onValueChange = { weight = it },
            values = Range.values()
        )

        // Edad
        TextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: age },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Rol Class
        Text("Role Class")
        RolClass.values().forEach { role ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = rolClass == role,
                    onClick = { rolClass = role }
                )
                Text(role.name, modifier = Modifier.padding(start = 2.dp))
            }
        }

        // Raza
        Text("Race")
        Race.values().forEach { r ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = race == r,
                    onClick = { race = r }
                )
                Text(r.name, modifier = Modifier.padding(start = 8.dp))
            }
        }




        // Selección de Items
        Text("Items")
        // Aquí tendrías que implementar un selector de items, por ejemplo un Dropdown o un Multiselect.
        // Aquí se simula la selección de items. Vamos a suponer que tienes un ViewModel para obtener los Items.
        val items = characterViewModel.getItems() // Implementa un método que obtenga los Items
        items.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = selectedItems.contains(item),
                    onCheckedChange = {
                        if (it) {
                            selectedItems = selectedItems + item
                        } else {
                            selectedItems = selectedItems - item
                        }
                    }
                )
                Text(item.name, modifier = Modifier.padding(start = 8.dp))
            }
        }

        // Botón para guardar
        Button(onClick = {
            val newCharacter = RolCharacter(
                name = name,
                description = description,
                rolClass = rolClass,
                race = race,
                height = height,
                weight = weight,
                age = age,
            )

            val items = characterViewModel.getItems()

            characterViewModel.insertCharacter(newCharacter)
        }) {
            Text("Insert Character")
        }
    }
}

@Composable
fun DropdownSelector(
    label: String,
    selectedValue: Range, // Usamos Range como ejemplo, pero puedes usar cualquier tipo de dato
    onValueChange: (Range) -> Unit,
    values: Array<Range>, // Array de las opciones a mostrar
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = selectedValue.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown", modifier = Modifier.clickable { expanded = !expanded })
            },
            modifier = Modifier.fillMaxWidth()
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
}



@Composable
fun CharacterDetail(){
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

