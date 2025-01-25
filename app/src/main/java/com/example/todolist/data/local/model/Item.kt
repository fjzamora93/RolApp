package com.example.todolist.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "itemTable")
data class Item(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "itemId")
    val itemId: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "isVersatile")
    val isVersatile: Boolean,

    @ColumnInfo(name = "isMartial")
    val isMartial: Boolean,

    @ColumnInfo(name = "isMelee")
    val isMelee: Boolean,

    @ColumnInfo(name = "rangedAttackPossible")
    val rangedAttackPossible: Boolean,

    @ColumnInfo(name = "rangeMelee")
    val rangeMelee: Float,

    @ColumnInfo(name = "isReach")
    val isReach: Boolean,

    @ColumnInfo(name = "distanceUnit")
    val distanceUnit: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "damageDice")
    val damageDice: String,

    @ColumnInfo(name = "versatileDice")
    val versatileDice: String?,

    @ColumnInfo(name = "reach")
    val reach: Float,

    @ColumnInfo(name = "range")
    val range: Float,

    @ColumnInfo(name = "longRange")
    val longRange: Float,

    @ColumnInfo(name = "isFinesse")
    val isFinesse: Boolean,

    @ColumnInfo(name = "isThrown")
    val isThrown: Boolean,

    @ColumnInfo(name = "isTwoHanded")
    val isTwoHanded: Boolean,

    @ColumnInfo(name = "requiresAmmunition")
    val requiresAmmunition: Boolean,

    @ColumnInfo(name = "requiresLoading")
    val requiresLoading: Boolean,

    @ColumnInfo(name = "isHeavy")
    val isHeavy: Boolean,

    @ColumnInfo(name = "isLight")
    val isLight: Boolean,

    @ColumnInfo(name = "isLance")
    val isLance: Boolean,

    @ColumnInfo(name = "isNet")
    val isNet: Boolean,

    @ColumnInfo(name = "isSimple")
    val isSimple: Boolean,

    @ColumnInfo(name = "isImprovised")
    val isImprovised: Boolean,

    @ColumnInfo(name = "document")
    val document: String,

    @ColumnInfo(name = "damageType")
    val damageType: String
)

