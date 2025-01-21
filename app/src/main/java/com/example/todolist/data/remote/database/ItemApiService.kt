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

        //@Query("page") page: Int? = null
    ): Response<ItemResponse>



    @GET("/")
    suspend fun getItemByName(
        @Query("name") name: String? = null,
    ): Response<Item>

}




interface ApiService {

    // Función para obtener la lista de items (en este caso "weapons")
    @GET("weapons/") // Suponiendo que "weapons/" es el endpoint de armas
    suspend fun getItems(
        //@Query("page") page: Int? = null
    ): Response<ApiResponse>






    // Función para obtener un item específico por nombre
    @GET("/") // Este endpoint debe ser ajustado según la documentación de la API
    suspend fun getItemByName(
        @Query("name") name: String? = null
    ): Response<Item> // Aquí la respuesta es de tipo Item, que es el modelo local que usas en Room

    // Agrega más funciones aquí para otros recursos, como "characters" o "weapons", según sea necesario
}



