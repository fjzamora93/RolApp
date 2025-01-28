package com.example.todolist.data.remote.database

import com.example.todolist.data.local.model.Item
import com.example.todolist.data.remote.model.ApiResponse
import com.example.todolist.data.remote.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ItemApiService {
    @GET("weapons/")
    suspend fun getItems(
    ): Response<ItemResponse>

    @GET("/")
    suspend fun getItemByName(
        @Query("name") name: String? = null,
    ): Response<Item>

}


interface ApiService {
    @GET("weapons/")
    suspend fun getItems(): Response<ApiResponse>
}



