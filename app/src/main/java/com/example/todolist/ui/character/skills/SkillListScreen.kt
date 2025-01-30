package com.example.todolist.ui.character.skills

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.data.local.model.Skill
import com.example.todolist.data.local.model.Spell
import com.example.todolist.di.LocalCharacterViewModel
import com.example.todolist.ui.character.CharacterViewModel
import com.example.todolist.ui.screens.components.BackButton
import com.example.todolist.ui.screens.components.RegularCard
import com.example.todolist.ui.screens.layout.MainLayout


@Composable
fun SkillListScreen(){
    MainLayout(){
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            SkillListBody()
            BackButton()
        }
    }
}


@Composable
fun SkillListBody(
    characterViewModel: CharacterViewModel = LocalCharacterViewModel.current,
    skillViewModel: SkillViewModel = hiltViewModel(),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val spellList by remember { skillViewModel.skillList }
    skillViewModel.getAllSKills()

    if (spellList == null) {
        Text("Cargando Hechizos...")
    } else {
        var spells by remember { mutableStateOf(spellList) }
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            spells.forEach { spell ->
                SkillCard(skill = spell)
            }
        }


    }
}


@Composable
fun SkillCard(skill: Skill, modifier: Modifier = Modifier) {
    RegularCard(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = skill.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Daño: ${skill.description}",
                style = MaterialTheme.typography.bodyMedium
            )


            // BOTÓN PARA AÑADIR A LA LISTA DE SKILLS DEL PERSONAJE
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Ver detalles")
            }
        }
    }
}