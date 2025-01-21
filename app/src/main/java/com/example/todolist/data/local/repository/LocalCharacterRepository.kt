package com.example.todolist.data.local.repository

import com.example.todolist.data.local.database.CharacterDao
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.domain.repository.CharacterRepository


/**
 * LAS CLASES DE ESTE REPOSITORIO DEBEN IMPLEMENTAR LA INTERFAZ DEL REPOSITORIO domain.repository.CharacterRepository
 *
 * */
class LocalCharacterRepository (
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
}
