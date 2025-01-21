package com.example.todolist.data.remote.model

import com.example.todolist.data.local.model.Item
import com.google.gson.annotations.SerializedName


// Modelo para cada item dentro de "results"
data class ItemResponse(
    @SerializedName("key") val key: String,
    @SerializedName("url") val url: String,
    @SerializedName("is_versatile") val isVersatile: Boolean,
    @SerializedName("is_martial") val isMartial: Boolean,
    @SerializedName("is_melee") val isMelee: Boolean,
    @SerializedName("ranged_attack_possible") val rangedAttackPossible: Boolean,
    @SerializedName("range_melee") val rangeMelee: Float,
    @SerializedName("is_reach") val isReach: Boolean,
    @SerializedName("distance_unit") val distanceUnit: String,
    @SerializedName("name") val name: String,
    @SerializedName("damage_dice") val damageDice: String,
    @SerializedName("versatile_dice") val versatileDice: String,
    @SerializedName("reach") val reach: Float,
    @SerializedName("range") val range: Float,
    @SerializedName("long_range") val longRange: Float,
    @SerializedName("is_finesse") val isFinesse: Boolean,
    @SerializedName("is_thrown") val isThrown: Boolean,
    @SerializedName("is_two_handed") val isTwoHanded: Boolean,
    @SerializedName("requires_ammunition") val requiresAmmunition: Boolean,
    @SerializedName("requires_loading") val requiresLoading: Boolean,
    @SerializedName("is_heavy") val isHeavy: Boolean,
    @SerializedName("is_light") val isLight: Boolean,
    @SerializedName("is_lance") val isLance: Boolean,
    @SerializedName("is_net") val isNet: Boolean,
    @SerializedName("is_simple") val isSimple: Boolean,
    @SerializedName("is_improvised") val isImprovised: Boolean,
    @SerializedName("document") val document: String,
    @SerializedName("damage_type") val damageType: String
)