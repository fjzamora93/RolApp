package com.example.todolist.domain.usecases

import com.example.todolist.domain.repository.TaskRepository
import com.example.todolist.data.local.model.Task
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        return taskRepository.deleteTask(task)
    }
}