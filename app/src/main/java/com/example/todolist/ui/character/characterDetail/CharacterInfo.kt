package com.example.todolist.ui.character.characterDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.model.RolCharacter


@Composable
fun InfoSection(
    editableCharacter: RolCharacter,
    onCharacterChange: (RolCharacter) -> Unit
){
    CharacterTextField(
        label = "Nombre",
        value = editableCharacter.name,
        onValueChange = { onCharacterChange(editableCharacter.copy(name = it)) }
    )

    CharacterTextField(
        label = "Descripcion",
        value = editableCharacter.description,
        onValueChange = { onCharacterChange(editableCharacter.copy(description = it)) }
    )
}



@Composable
fun CharacterTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEdditing by remember { mutableStateOf(false) }
    var localValue by remember { mutableStateOf(value) }

    if (isEdditing) {
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = localValue,
                onValueChange = {
                    localValue = it
                    onValueChange(it)
                })
            IconButton(onClick = { isEdditing = false }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    } else {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = label, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = value, style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = { isEdditing = true }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
            }
        }
    }
}