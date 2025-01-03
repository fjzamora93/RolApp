package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rolCharacterTable")
data class RolCharacter(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var completed: Boolean = false // <- Aquí
)

