package com.example.todolist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.database.CharacterDao
import com.example.todolist.data.local.database.ItemDao
import com.example.todolist.data.local.database.MyDatabase
import com.example.todolist.data.local.repository.LocalCharacterRepository
import com.example.todolist.data.local.repository.LocalSkillRepository
import com.example.todolist.data.remote.database.ApiService
import com.example.todolist.domain.repository.CharacterRepository
import com.example.todolist.domain.repository.SkillRepository
import com.example.todolist.util.Constants.MY_DATA_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("https://api.open5e.com/v2/")
            .client(client)  // Pasa el cliente configurado
            .addConverterFactory(GsonConverterFactory.create())  // Usa el convertidor de Gson
            .build()
    }



    /** INSTANCIA ÚNICA DEL SERVICIO API */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
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
        return LocalCharacterRepository(
            database.characterDao(),
            database.getItemDao()
        )
    }

    @Provides
    @Singleton
    fun provideSkillRepository(database: MyDatabase): SkillRepository {
        return LocalSkillRepository(
            database.characterDao(),
        )
    }

    @Provides
    @Singleton
    fun provideCharacterDao(database: MyDatabase): CharacterDao {
        return database.characterDao()
    }
    @Provides
    @Singleton
    fun provideItemDao(database: MyDatabase): ItemDao {
        return database.getItemDao()
    }



}
