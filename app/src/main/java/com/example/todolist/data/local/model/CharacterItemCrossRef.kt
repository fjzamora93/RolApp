package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "character_item_cross_ref",
    primaryKeys = ["characterId", "itemId"],
    foreignKeys = [
        ForeignKey(
            entity = RolCharacter::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CharacterItemCrossRef(
    val characterId: Int,
    val itemId: Int
)
