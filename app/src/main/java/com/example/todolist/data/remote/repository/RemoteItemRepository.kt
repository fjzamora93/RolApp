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

    fun fetchItems(
        filter: String,
        onSuccess: (List<Item>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamamos a la API para obtener los items
            val call = apiService.getItems() // Si necesitas paginación, pasa un parámetro `page`
            val apiResponse = call.body() // Aquí recibimos ApiResponse

            println("Realizando búsqueda con filtro dentro del REpositorio: $apiResponse y $filter")

            withContext(Dispatchers.Main) {
                if (call.isSuccessful && filter != "error") {
                    // Extraemos la lista de items de ApiResponse
                    val itemList: List<Item> = apiResponse?.results?.map { mapApiItemToLocal(it) } ?: emptyList()
                    println("Items obtenidos: $itemList")
                    onSuccess(itemList)
                } else {
                    // Si la respuesta no es exitosa o si hay un filtro de error, manejamos el error
                    println("Error: ${call.errorBody()?.string()}")
                    onError()
                }
            }
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