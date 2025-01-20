package com.example.todolist.data.local.repository

import com.example.todolist.data.local.database.ItemApiService
import com.example.todolist.data.local.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RemoteItemRepository @Inject constructor(
    private val itemApiService: ItemApiService
) {

    fun fetchItems(
        filter: String,
        onSuccess: (List<Item>) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = itemApiService.getItems(
            )
            val movies = call.body()
            println("Realizando búsqueda con filtro dentro del REpositorio: $movies y $filter")
            withContext(Dispatchers.Main) {
                if (call.isSuccessful && filter != "error") {
                    val itemList: List<Item> = movies?.Search ?: emptyList()
                    onSuccess(itemList)
                } else {
                    println("Error: ${call.errorBody()?.string()}")
                    onError()
                }
            }
        }

    }



}