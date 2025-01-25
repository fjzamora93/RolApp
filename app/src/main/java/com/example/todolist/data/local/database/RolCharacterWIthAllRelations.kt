package com.example.todolist.data.local.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.todolist.data.local.model.CharacterItemCrossRef
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.Skill

data class RolCharacterWithAllRelations(

    // Relación de muchos a muchos
    @Embedded val rolCharacter: RolCharacter,

    // Relación de muchos a muchos
    @Relation(
        parentColumn = "id", // columna de la entidad principal
        entityColumn = "itemId", // columna de la entidad relacionada
        entity = Item::class
    )
    val items: List<Item>,


    // Relación de 1:1
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val skills: List<Skill>, // Lista de habilidades



)

