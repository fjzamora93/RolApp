package com.example.todolist.presentation.screens.task_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.model.Task
import com.example.todolist.data.local.model.TaskPriority
import com.example.todolist.domain.usecases.DeleteTaskUseCase
import com.example.todolist.domain.usecases.GetSpecificTaskUseCase
import com.example.todolist.domain.usecases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getSpecificTaskUseCase: GetSpecificTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    // StateFlow to hold the current task details
    private var _currentTask = MutableStateFlow(Task())
    val currentTask = _currentTask.asStateFlow()

    // StateFlow to manage delete task dialog visibility
    private var _showDeleteTaskDialog = MutableStateFlow(false)
    val showDeleteTaskDialog = _showDeleteTaskDialog.asStateFlow()

    // StateFlows to manage priority chips
    private val _noPriorityChip = MutableStateFlow(false)
    val noPriorityChip = _noPriorityChip.asStateFlow()

    private val _lowChip = MutableStateFlow(false)
    val lowChip = _lowChip.asStateFlow()

    private val _mediumChip = MutableStateFlow(false)
    val mediumChip = _mediumChip.asStateFlow()

    private val _highChip = MutableStateFlow(false)
    val highChip = _highChip.asStateFlow()

    // Function to load a specific task by ID
    suspend fun loadTask(taskId: Int) {
        viewModelScope.launch {
            _currentTask.value = getSpecificTaskUseCase(taskId)!!
            setUpPriority()
        }
    }

    // Function to update task completion status
    fun updateCompleteTask() {
        viewModelScope.launch {
            val updateTask = _currentTask.value.copy(taskCompleted = !_currentTask.value.taskCompleted)
            updateTaskUseCase(updateTask)
            _currentTask.value = updateTask
        }
    }

    // Function to update task priority
    fun updatePriorityTask(newPriority: TaskPriority) {
        viewModelScope.launch {
            val updateTask = _currentTask.value.copy(taskPriority = newPriority)
            updateTaskUseCase(updateTask)
            _currentTask.value = updateTask
        }
    }

    // Function to clear current task
    fun clearTask() {
        _currentTask.value = Task()
    }

    // Function to update task when back button is pressed
    fun onBackButtonPressed() {
        viewModelScope.launch {
            val updateTask = _currentTask.value.copy(
                taskName = _currentTask.value.taskName,
                taskDescription = _currentTask.value.taskDescription
            )
            updateTaskUseCase(updateTask)
            _currentTask.value = updateTask
        }
    }

    // Function to toggle delete task dialog visibility
    fun showTaskPopupDelete(show: Boolean) {
        _showDeleteTaskDialog.value = show
    }

    // Function to delete a task
    fun deleteTaskOnTrashClick(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    // Function to handle task name change
    fun onTaskNameChange(value: String) {
        _currentTask.value = _currentTask.value.copy(taskName = value)
    }

    // Function to handle task description change
    fun onTaskDescriptionChange(value: String) {
        _currentTask.value = _currentTask.value.copy(taskDescription = value)
    }

    // Function to set up priority chips based on current task priority
    private fun setUpPriority() {
        _noPriorityChip.value = false
        _lowChip.value = false
        _mediumChip.value = false
        _highChip.value = false

        when (_currentTask.value.taskPriority) {
            TaskPriority.NO_PRIORITY -> _noPriorityChip.value = true
            TaskPriority.LOW -> _lowChip.value = true
            TaskPriority.MEDIUM -> _mediumChip.value = true
            TaskPriority.HIGH -> _highChip.value = true
        }
    }

    // Function to update chip selection based on label
    fun updateChipOnClick(label: String) {
        _noPriorityChip.value = false
        _lowChip.value = false
        _mediumChip.value = false
        _highChip.value = false

        when (label) {
            "no priority" -> _noPriorityChip.value = true
            "low" -> _lowChip.value = true
            "medium" -> _mediumChip.value = true
            "high" -> _highChip.value = true
        }
    }
}
