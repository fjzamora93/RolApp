package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.local.database.MyDatabase
import com.example.todolist.data.local.repository.TaskRepositoryImpl
import com.example.todolist.domain.repository.TaskRepository
import com.example.todolist.util.Constants.MY_DATA_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {

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
    fun provideTaskRepository(database: MyDatabase): TaskRepository {
        return TaskRepositoryImpl(database.getTaskDao())
    }
}
