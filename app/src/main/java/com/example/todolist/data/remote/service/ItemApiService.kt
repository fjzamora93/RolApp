package com.example.todolist.data.remote.service

import com.example.todolist.data.remote.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query





interface ApiService {
    @GET("weapons/")
    suspend fun getItems(): Response<ApiResponse>



    @GET("spells/")
    suspend fun getSpells(): Response<ApiResponse>


    // EJEMPLO: https://api.open5e.com/v2/spells/?name=Fireball
    // https://api.open5e.com/v2/spells/?key=a5e-ag_fireball
    @GET("spells/")
    suspend fun getSpellsByKey(
        @Query("key") name: String
    ): Response<ApiResponse>


    // EJEMPLO: https://api.open5e.com/v2/spells/?level__lte=3

    @GET("spells/")
    suspend fun getSpellsByLevel(
        @Query("level__lte") level: Int
    ): Response<ApiResponse>




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