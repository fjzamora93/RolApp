package com.example.todolist.ui.character.characterDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.model.RolCharacter


@Composable
fun StatSection(
    editableCharacter: RolCharacter,
    onCharacterChange: (RolCharacter) -> Unit
) {
    CharacterBar(
        label = "HP",
        maxValue = editableCharacter.hp,
        localValue = editableCharacter.currentHp,
        onValueChanged = { onCharacterChange(editableCharacter.copy(currentHp = it)) }
    )

    CharacterBar(
        label = "AP",
        color = Color(0xFFADD8E6),
        maxValue = editableCharacter.ap,
        localValue = editableCharacter.currentAp,
        onValueChanged = { onCharacterChange(editableCharacter.copy(currentAp = it)) }
    )


    CharacterNumberField(
        label = "Fuerza",
        value = editableCharacter.strength,
        onValueChange = { onCharacterChange(editableCharacter.copy(strength = it)) }
    )
    CharacterNumberField(
        label = "Destreza",
        value = editableCharacter.dexterity,
        onValueChange = { onCharacterChange(editableCharacter.copy(dexterity = it)) }
    )

    CharacterNumberField(
        label = "Constitucion",
        value = editableCharacter.constitution,
        onValueChange = { onCharacterChange(editableCharacter.copy(constitution = it)) }
    )

    CharacterNumberField(
        label = "Inteligencia",
        value = editableCharacter.intelligence,
        onValueChange = { onCharacterChange(editableCharacter.copy(intelligence = it)) }
    )
    CharacterNumberField(
        label = "Sabiduría",
        value = editableCharacter.wisdom,
        onValueChange = { onCharacterChange(editableCharacter.copy(wisdom = it)) }
    )

    CharacterNumberField(
        label = "Carisma",
        value = editableCharacter.charisma,
        onValueChange = { onCharacterChange(editableCharacter.copy(charisma = it)) }
    )
}

@Composable
fun CharacterNumberField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var localValue by remember { mutableStateOf(value) }
    Column(modifier = modifier.padding(4.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = {
                localValue -= 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrement")
            }
            Text(text = localValue.toString(), style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = {
                localValue += 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
            }
        }
    }
}


@Composable
fun CharacterBar(
    label: String = "",
    color: Color = Color.Green,
    maxValue: Int = 20,
    localValue: Int = 10,
    onValueChanged: (Int) -> Unit
) {
    // Calculamos el porcentaje de llenado de la barra en función de los valores proporcionados
    val progress = try {
        // Aseguramos que maxValue no sea cero antes de dividir
        if (maxValue != 0) {
            localValue.toFloat() / maxValue.toFloat()
        } else {
            1f  // Si maxValue es 0, asignamos un valor por defecto
        }
    } catch (e: ArithmeticException) {
        1f  // En caso de que se genere un error de división, asignamos un valor por defecto
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "$label: $localValue/$maxValue", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (localValue > 0) {
                    onValueChanged(localValue - 1)
                }
            }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrement")
            }

            // Barra de progreso
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .background(Color.Gray.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
            ) {
                // Parte que muestra el progreso (verde)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .background(color, shape = RoundedCornerShape(8.dp))
                )
            }

            IconButton(onClick = {
                println("Valor actual: $localValue - Valor máximo: $maxValue")
                if (localValue < maxValue) {
                    onValueChanged(localValue + 1)
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
            }
        }
    }

}
