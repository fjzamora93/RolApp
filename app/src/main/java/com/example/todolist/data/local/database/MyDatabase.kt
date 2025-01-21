package com.example.todolist.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.local.model.Skill

@Database(entities = [RolCharacter::class, Skill::class, Item::class], version = 7)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getItemDao(): ItemDao
    abstract fun characterDao(): CharacterDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration() // DESTRUYE LA BASE DE DATOS ANTERIOR AL MIGRAR
                    .build() // Remove addMigrations() if present
                INSTANCE= instance
                instance
            }
        }
    }
}