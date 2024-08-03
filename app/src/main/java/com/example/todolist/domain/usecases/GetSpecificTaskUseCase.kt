package com.example.todolist.domain.usecases

import com.example.todolist.domain.repository.TaskRepository
import com.example.todolist.data.local.model.Task
import javax.inject.Inject

class GetSpecificTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskId: Int): Task? {
        return taskRepository.getTaskById(taskId)
    }
}