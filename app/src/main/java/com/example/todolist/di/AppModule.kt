package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.local.database.ItemApiService
import com.example.todolist.data.local.database.MyDatabase
import com.example.todolist.data.local.repository.CharacterRepositoryImpl
import com.example.todolist.domain.repository.CharacterRepository
import com.example.todolist.domain.repository.TaskRepository
import com.example.todolist.util.Constants.MY_DATA_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {


    /** INSTANCIA ÚNICA DE RETROFIT + CONVERTIDOR JSON
     * API UTILIZADA: https://api.open5e.com/
     *
     * EJEMPLOS
     * https://api.open5e.com/v2/weapons/
     * https://api.open5e.com/v2/spells/
     *
     * https://api.open5e.com/v2/weapons/?name=Dagger
     * */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Configura el cliente OkHttp con tiempo de espera personalizado
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo de conexión
            .readTimeout(30, TimeUnit.SECONDS)     // Tiempo de lectura
            .writeTimeout(30, TimeUnit.SECONDS)    // Tiempo de escritura
            .build()

        return Retrofit.Builder()
            .baseUrl("https://tu-api.com/")
            .client(client)  // Pasa el cliente configurado
            .addConverterFactory(GsonConverterFactory.create())  // Usa el convertidor de Gson
            .build()
    }

    /** INSTANCIA ÚNICA DEL SERVICIO API */
    @Provides
    @Singleton
    fun provideItemApiService(retrofit: Retrofit): ItemApiService {
        return retrofit.create(ItemApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): MyDatabase {
        return Room.databaseBuilder(
            app,
            MyDatabase::class.java,
            MY_DATA_BASE
        ).build()
    }



    @Provides
    @Singleton
    fun provideCharacterRepository(database: MyDatabase): CharacterRepository {
        return CharacterRepositoryImpl(database.characterDao())
    }



}
