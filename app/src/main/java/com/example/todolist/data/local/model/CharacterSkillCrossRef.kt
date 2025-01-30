package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "character_skill_table",
    primaryKeys = ["characterId", "skillId"],
    foreignKeys = [
        ForeignKey(
            entity = RolCharacter::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Skill::class,
            parentColumns = ["id"],
            childColumns = ["skillId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CharacterSkillCrossRef(
    val characterId: Int,
    val skillId: Int
)
