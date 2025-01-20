package com.example.todolist.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.local.model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM itemTable")
    suspend fun getItemList(): List<Item>

    @Query("SELECT * FROM itemTable WHERE itemKey = :itemId")
    suspend fun getTaskById(itemId: String): Item?

    @Insert
    suspend fun insertTask(task: Item)

    @Update
    suspend fun updateTask(task: Item)

    @Delete
    suspend fun deleteTask(task: Item)
}