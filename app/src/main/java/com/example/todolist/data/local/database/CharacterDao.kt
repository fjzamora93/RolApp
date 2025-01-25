package com.example.todolist.data.local.database

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy
import androidx.room.Query;
import androidx.room.Transaction
import androidx.room.Update;
import com.example.todolist.data.local.model.CharacterItemCrossRef
import com.example.todolist.data.local.model.RolCharacter;



@Dao
interface CharacterDao {
    @Query("SELECT * FROM rolCharacterTable")
    suspend fun getAllCharacters(): List<RolCharacter>

    @Transaction
    @Query("SELECT * FROM rolCharacterTable WHERE id = :characterId")
    suspend fun getCharacter(characterId: Int): RolCharacter?

    // Obtener un personaje específico con todas sus relaciones
    @Transaction
    @Query("SELECT * FROM rolCharacterTable WHERE id = :characterId")
    suspend fun getCharacterWithRelations(characterId: Int): RolCharacterWithAllRelations?


    @Insert
    suspend fun insertCharacter(character: RolCharacter)

    @Update
    suspend fun updateCharacter(character: RolCharacter)

    @Delete
    suspend fun deleteCharacter(character: RolCharacter)

    // Método para añadir un item a un rol character (relación)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToCharacter(characterItemCrossRef: CharacterItemCrossRef)


}
