package com.example.todolist.data.local.repository

import com.example.todolist.data.local.database.TaskDao
import com.example.todolist.data.local.model.Task
import com.example.todolist.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        return taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        return taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return taskDao.deleteTask(task)
    }
}