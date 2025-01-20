package com.example.todolist.data.local.model

import com.google.gson.annotations.SerializedName

data class ItemResponse (
    @SerializedName("Search") val Search: List<Item>,
    @SerializedName("Response") val Response: String?
){

}