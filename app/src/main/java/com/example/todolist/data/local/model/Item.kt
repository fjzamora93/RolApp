package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "itemTable") // Asegúrate de usar un nombre adecuado para la tabla
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String = "",
    var description: String = ""
)
