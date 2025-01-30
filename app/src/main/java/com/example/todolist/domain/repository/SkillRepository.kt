package com.example.todolist.domain.repository

import android.content.Context
import com.example.todolist.data.local.model.Skill

interface SkillRepository {
    suspend fun readFromJson(context: Context) : List<Skill>
    suspend fun fetchSkillsFromCharacter(characterId: Int, skillId: Int) : List<Skill>
}