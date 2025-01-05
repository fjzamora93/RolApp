package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "skillTable",
    foreignKeys = [ForeignKey(
        entity = RolCharacter::class,
        parentColumns = ["id"], // Columna en RolCharacter
        childColumns = ["characterId"], // Columna en Skill
        onDelete = ForeignKey.CASCADE) // Si se elimina el RolCharacter, también se elimina Skill
    ],
    indices = [Index(value = ["characterId"])] // Crear un índice sobre la columna characterId
)
data class Skill(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String = "",
    var description: String = "",

    // Relación con RolCharacter
    val characterId: Int // Este es el ID del personaje que posee la habilidad
)


