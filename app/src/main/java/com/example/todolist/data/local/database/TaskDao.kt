package com.example.todolist.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.local.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskTable")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM taskTable WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}