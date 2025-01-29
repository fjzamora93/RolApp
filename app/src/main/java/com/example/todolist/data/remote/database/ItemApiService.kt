package com.example.todolist.data.remote.database

import com.example.todolist.data.local.model.Item
import com.example.todolist.data.remote.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query





interface ApiService {
    @GET("weapons/")
    suspend fun getItems(): Response<ApiResponse>

    @GET("spells/")
    suspend fun getSpells(): Response<ApiResponse>

    @GET("feat/")
    suspend fun getFeats(): Response<ApiResponse>
}



//
//interface ItemApiService {
//    @GET("weapons/")
//    suspend fun getItems(
//    ): Response<ItemResponse>
//
//    @GET("/")
//    suspend fun getItemByName(
//        @Query("name") name: String? = null,
//    ): Response<Item>
//
//}