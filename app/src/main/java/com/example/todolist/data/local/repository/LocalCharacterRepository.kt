package com.example.todolist.data.local.repository

import com.example.todolist.data.local.database.CharacterDao
import com.example.todolist.data.local.database.ItemDao
import com.example.todolist.data.local.database.RolCharacterWithAllRelations
import com.example.todolist.data.local.model.CharacterItemCrossRef
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.domain.repository.CharacterRepository
import javax.inject.Inject


/**
 * LAS CLASES DE ESTE REPOSITORIO DEBEN IMPLEMENTAR LA INTERFAZ DEL REPOSITORIO domain.repository.CharacterRepository
 *
 * */
class LocalCharacterRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val itemDao: ItemDao
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

    // TODAS LAS RELACIONES
    override suspend fun getCharacterWithRelations(characterId: Int): RolCharacterWithAllRelations? {
        return characterDao.getCharacterWithRelations(characterId)
    }

    override suspend fun addItemToCharacter(character: RolCharacter, item: Item) {
        itemDao.insertItem(item) // ANtes de insertar la relación, nos aseguramos de que el item, que viene de la API, está dentro de SQLITE
        val characterCrossRef = CharacterItemCrossRef(character.id, item.id)
        characterDao.addItemToCharacter(characterCrossRef)
    }

}
