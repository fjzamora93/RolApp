package com.example.todolist.ui.character.skills

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.todolist.data.local.model.Skill
import com.example.todolist.data.local.repository.LocalCharacterRepository
import com.example.todolist.data.local.repository.LocalSkillRepository
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillViewModel  @Inject constructor(
    private val localCharacterRepository: LocalCharacterRepository,
    private val skillRepository: LocalSkillRepository,
    @ApplicationContext private val context: Context
) :  ViewModel() {

    private val _skillList = mutableStateOf<List<Skill>>(emptyList())
    val skillList: State<List<Skill>> get() = _skillList


    fun getAllSKills(){
        viewModelScope.launch {
            _skillList.value = skillRepository.readFromJson(context)
            println("Skills: ${skillList.value}")
        }
    }

    fun getSkillsFromCharacter(
        characterId: Int,
        skillId: Int,
    ){
        viewModelScope.launch {
            skillRepository.fetchSkillsFromCharacter(characterId, skillId)
            println("Skills: ${skillList.value}")
        }
    }

}