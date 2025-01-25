package com.example.todolist.domain.repository
import com.example.todolist.data.local.database.RolCharacterWithAllRelations
import com.example.todolist.data.local.model.RolCharacter

interface CharacterRepository {
    suspend fun getAllCharacters(): List<RolCharacter>
    suspend fun getCharacterById(id: Int): RolCharacter?
    suspend fun insertCharacter(character: RolCharacter)
    suspend fun updateCharacter(character: RolCharacter)
    suspend fun deleteCharacter(character: RolCharacter)

    // Para obtener las relaciones de un personaje
    suspend fun getCharacterWithRelations(characterId: Int): RolCharacterWithAllRelations?
    suspend fun addItemToCharacter(characterId: Int, itemId: String)

}