package com.example.todolist.data.local.repository

import android.content.Context
import com.example.todolist.data.local.database.CharacterDao
import com.example.todolist.data.local.model.Skill
import com.example.todolist.domain.repository.SkillRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class LocalSkillRepository  @Inject constructor (
    private val characterDao: CharacterDao,
) : SkillRepository {

    override suspend fun readFromJson(context: Context): List<Skill> {
        val jsonString: String
        try {
            jsonString = context.assets.open("skills.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return emptyList() // Si hay un error, devuelve una lista vacía
        }

        // Convertir el JSON en una lista de objetos Ejemplo
        val listType = object : TypeToken<List<Skill>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


    override suspend fun fetchSkillsFromCharacter(
        characterId: Int,
        skillId: Int,
    ) : List<Skill> {
        println("Fetching skills from character with ID: $characterId and skill ID: $skillId")
        return emptyList()
    }
}