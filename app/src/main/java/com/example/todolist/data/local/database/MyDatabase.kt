package com.example.todolist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.Task

@Database(entities = [Task::class, RolCharacter::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun characterDao(): CharacterDao
}