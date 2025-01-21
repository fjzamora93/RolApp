package com.example.todolist.data.remote.model

import com.example.todolist.data.local.model.Item
import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Item> // Aquí es donde comes los datos de los objetos
)