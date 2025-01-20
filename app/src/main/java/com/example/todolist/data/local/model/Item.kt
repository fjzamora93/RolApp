package com.example.todolist.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "itemTable") // Asegúrate de usar un nombre adecuado para la tabla
data class Item(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "itemKey") // Nombre en la base de datos
    val key: String,

    val url: String,
    val is_versatile: Boolean,
    val is_martial: Boolean,
    val is_melee: Boolean,
    val ranged_attack_possible: Boolean,
    val range_melee: Float,
    val is_reach: Boolean,
    val distance_unit: String,
    val name: String,
    val damage_dice: String,
    val versatile_dice: String?,
    val reach: Float,
    val range: Float,
    val long_range: Float,
    val is_finesse: Boolean,
    val is_thrown: Boolean,
    val is_two_handed: Boolean,
    val requires_ammunition: Boolean,
    val requires_loading: Boolean,
    val is_heavy: Boolean,
    val is_light: Boolean,
    val is_lance: Boolean,
    val is_net: Boolean,
    val is_simple: Boolean,
    val is_improvised: Boolean,
    val document: String,
    val damage_type: String
)
