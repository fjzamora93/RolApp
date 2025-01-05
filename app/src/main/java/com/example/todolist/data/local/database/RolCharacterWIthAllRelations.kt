package com.example.todolist.data.local.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.Skill

data class RolCharacterWithAllRelations(

    @Embedded val rolCharacter: RolCharacter, // El personaje

    // Relación de 1:1
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val skills: List<Skill>, // Lista de habilidades


    // Relación de muchos a muchos
    @Relation(
        parentColumn = "id", // Referencia al id de RolCharacter
        entityColumn = "characterId" // Referencia al characterId en la tabla intermedia
    )
    val items: List<Item>, // Lista de objetos

)

