package com.example.todolist.data.local.repository

import com.example.todolist.data.local.database.CharacterDao
import com.example.todolist.data.local.database.RolCharacterWithAllRelations
import com.example.todolist.data.local.model.CharacterItemCrossRef
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.domain.repository.CharacterRepository
import javax.inject.Inject


/**
 * LAS CLASES DE ESTE REPOSITORIO DEBEN IMPLEMENTAR LA INTERFAZ DEL REPOSITORIO domain.repository.CharacterRepository
 *
 * */
class LocalCharacterRepository @Inject constructor(
    private val characterDao: CharacterDao

) : CharacterRepository {

    override suspend fun getAllCharacters(): List<RolCharacter> {
        return characterDao.getAllCharacters()
    }

    override suspend fun getCharacterById(id: Int): RolCharacter? {
        return characterDao.getCharacter(id)
    }

    override suspend fun insertCharacter(character: RolCharacter) {
        characterDao.insertCharacter(character)
    }

    override suspend fun updateCharacter(character: RolCharacter) {
        character.calculateHp()
        characterDao.updateCharacter(character)
    }

    override suspend fun deleteCharacter(character: RolCharacter) {
        characterDao.deleteCharacter(character)
    }

    // Obtener un personaje con todas sus relaciones
    override suspend fun getCharacterWithRelations(characterId: Int): RolCharacterWithAllRelations? {
        println("Recuperando en el repositorio: ${characterDao.getCharacterWithRelations(characterId)}")
        return characterDao.getCharacterWithRelations(characterId)
    }

    override suspend fun addItemToCharacter(characterId: Int, itemId: String) {
        println("Recuperando en el repositorio: ${characterDao.getCharacterWithRelations(characterId)}")
        val characterId = 1 // ID del personaje
        val itemId = "item123" // ID del item

        // Crear la instancia de la relación
        val crossRef = CharacterItemCrossRef(characterId, itemId)

        // Insertar la relación en la tabla intermedia
        characterDao.addItemToCharacter(crossRef)
    }

}
