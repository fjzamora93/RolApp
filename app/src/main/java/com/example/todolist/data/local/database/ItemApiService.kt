package com.example.todolist.data.local.database

import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ItemApiService {
    @GET("weapons/")
    suspend fun getItems(

        //@Query("page") page: Int? = null
    ): Response<ItemResponse>

    @GET("/")
    suspend fun getItemByName(
        @Query("name") name: String? = null,
    ): Response<Item>

}







