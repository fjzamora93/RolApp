package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "taskTable")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0,
    var taskName: String = "",
    var taskDescription: String = "",
    var taskCompleted: Boolean = false,
    var taskPriority: TaskPriority = TaskPriority.NO_PRIORITY
)