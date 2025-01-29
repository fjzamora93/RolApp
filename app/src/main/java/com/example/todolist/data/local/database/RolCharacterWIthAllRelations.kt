package com.example.todolist.data.local.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.todolist.data.local.model.CharacterItemCrossRef
import com.example.todolist.data.local.model.CharacterSpellCrossRef
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.Skill
import com.example.todolist.data.local.model.Spell

data class RolCharacterWithAllRelations(
    @Embedded val rolCharacter: RolCharacter,

    // RELACIÓN DE MUCHOS A MUCHOS
    @Relation(
        parentColumn = "id", // id en la tabla RolCharacter
        entityColumn = "id", // id de la tabla CharacterItemCrossRef que referencia a Item
        associateBy = Junction(
            CharacterItemCrossRef::class,
            parentColumn = "characterId", // id en CharacterItemCrossRef que referencia a RolCharacter
            entityColumn = "itemId" // id en CharacterItemCrossRef que referencia a Item
        )
    )
    val items: List<Item>,


    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CharacterSpellCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "spellId"
        )
    )
    val spells: List<Spell>,




    // RELACIÓN DE UNO A MUCHOS (PENDIENTE DE IMPLEMENTAR)
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val skills: List<Skill>,
)

