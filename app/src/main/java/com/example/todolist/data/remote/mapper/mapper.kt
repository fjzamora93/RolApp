package com.example.todolist.data.remote.mapper

import com.example.todolist.data.local.model.Item
import com.example.todolist.data.remote.model.ItemResponse


fun mapApiItemToLocal(itemResponse: ItemResponse): Item {
    return Item(
        id = itemResponse.key,
        url = itemResponse.url,
        isVersatile = itemResponse.isVersatile,
        isMartial = itemResponse.isMartial,
        isMelee = itemResponse.isMelee,
        rangedAttackPossible = itemResponse.rangedAttackPossible,
        rangeMelee = itemResponse.rangeMelee,
        isReach = itemResponse.isReach,
        distanceUnit = itemResponse.distanceUnit,
        name = itemResponse.name,
        damageDice = itemResponse.damageDice,
        versatileDice = itemResponse.versatileDice,
        reach = itemResponse.reach,
        range = itemResponse.range,
        longRange = itemResponse.longRange,
        isFinesse = itemResponse.isFinesse,
        isThrown = itemResponse.isThrown,
        isTwoHanded = itemResponse.isTwoHanded,
        requiresAmmunition = itemResponse.requiresAmmunition,
        requiresLoading = itemResponse.requiresLoading,
        isHeavy = itemResponse.isHeavy,
        isLight = itemResponse.isLight,
        isLance = itemResponse.isLance,
        isNet = itemResponse.isNet,
        isSimple = itemResponse.isSimple,
        isImprovised = itemResponse.isImprovised,
        document = itemResponse.document,
        damageType = itemResponse.damageType
    )
}
