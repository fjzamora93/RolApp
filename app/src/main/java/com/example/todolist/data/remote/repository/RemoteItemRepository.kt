package com.example.todolist.data.remote.repository

import com.example.todolist.data.remote.database.ItemApiService
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.remote.database.ApiService
import com.example.todolist.data.remote.model.ItemResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject



class RemoteItemRepository @Inject constructor(
    private val itemApiService: ItemApiService,
    private val apiService: ApiService
) {

    suspend fun fetchItems(filter: String): Result<List<Item>> {
        // El bloque return try nos devolverá la última sentencia que haya en el try o catch.
        return try {
            val response = apiService.getItems()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                val itemList: List<Item> = apiResponse?.results?.map { mapApiItemToLocal(it) } ?: emptyList()

                // Filtramos los resultados si es necesario
                val filteredItems = if (filter.isNotEmpty()) {
                    itemList.filter { it.name.contains(filter, ignoreCase = true) }
                } else {
                    itemList
                }

                Result.success(filteredItems)
            } else {
                // Manejamos errores HTTP
                Result.failure(Exception("Error en la respuesta: ${response.code()}"))
            }
        } catch (e: Exception) {
            // Manejamos excepciones de red u otras
            Result.failure(e)
        }
    }

    // Función para mapear el modelo de la API (ApiItemResponse) al modelo local (Item)
    private fun mapApiItemToLocal(apiItem: ItemResponse): Item {
        return Item(
            key = apiItem.key,
            url = apiItem.url,
            isVersatile = apiItem.isVersatile,
            isMartial = apiItem.isMartial,
            isMelee = apiItem.isMelee,
            rangedAttackPossible = apiItem.rangedAttackPossible,
            rangeMelee = apiItem.rangeMelee,
            isReach = apiItem.isReach,
            distanceUnit = apiItem.distanceUnit,
            name = apiItem.name,
            damageDice = apiItem.damageDice,
            versatileDice = apiItem.versatileDice,
            reach = apiItem.reach,
            range = apiItem.range,
            longRange = apiItem.longRange,
            isFinesse = apiItem.isFinesse,
            isThrown = apiItem.isThrown,
            isTwoHanded = apiItem.isTwoHanded,
            requiresAmmunition = apiItem.requiresAmmunition,
            requiresLoading = apiItem.requiresLoading,
            isHeavy = apiItem.isHeavy,
            isLight = apiItem.isLight,
            isLance = apiItem.isLance,
            isNet = apiItem.isNet,
            isSimple = apiItem.isSimple,
            isImprovised = apiItem.isImprovised,
            document = apiItem.document,
            // Si damageType es una URL o un objeto, extráele solo el valor relevante
            damageType = apiItem.damageType
        )
    }

}