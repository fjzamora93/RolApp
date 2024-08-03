package com.example.todolist.presentation.screens.task_list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.model.Task
import com.example.todolist.data.local.model.TaskPriority
import com.example.todolist.domain.usecases.CreateTaskUseCase
import com.example.todolist.domain.usecases.DeleteTaskUseCase
import com.example.todolist.domain.usecases.GetAllTaskUseCase
import com.example.todolist.domain.usecases.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {

    // StateFlow for the list of tasks
    private val _taskList = MutableStateFlow(mutableListOf<Task>())
    val taskList = _taskList.asStateFlow()

    // StateFlow for the list of completed tasks
    private val _taskListCompleted = MutableStateFlow(mutableListOf<Task>())
    val taskListCompleted = _taskListCompleted.asStateFlow()

    // StateFlow for showing the add task dialog
    private val _showAddTaskDialog = MutableStateFlow(false)
    val showAddTaskDialog = _showAddTaskDialog.asStateFlow()

    // StateFlow for showing the delete task dialog
    private val _showDeleteTaskDialog = MutableStateFlow(false)
    val showDeleteTaskDialog = _showDeleteTaskDialog.asStateFlow()

    // StateFlow for the task name
    private val _taskName = MutableStateFlow("")
    val taskName = _taskName.asStateFlow()

    // StateFlow for the task name error text
    private val _taskNameErrorText = MutableStateFlow("")
    val taskNameErrorText = _taskNameErrorText.asStateFlow()

    // StateFlow for the task to be deleted
    private val _deleteTask = MutableStateFlow(Task())
    val deleteTask = _deleteTask.asStateFlow()

    // StateFlow for the visibility of the description text area
    private val _textAreaDescriptionVisible = MutableStateFlow(false)
    val textAreaDescriptionVisible = _textAreaDescriptionVisible.asStateFlow()

    // StateFlow for the task priority
    private val _taskPriority = MutableStateFlow(TaskPriority.NO_PRIORITY)
    val taskPriority = _taskPriority.asStateFlow()

    // StateFlow for the button description color
    private val _buttonDescColor = MutableStateFlow(Color(0xFF9E9E9E))
    val buttonDescColor = _buttonDescColor.asStateFlow()

    // StateFlow for the "All" chip selection
    private val _allChip = MutableStateFlow(true)
    val allChip = _allChip.asStateFlow()

    // StateFlow for the "Priority" chip selection
    private val _priorityChip = MutableStateFlow(false)
    val priorityChip = _priorityChip.asStateFlow()

    // StateFlow for the task description
    private val _taskDescription = MutableStateFlow("")
    val taskDescription = _taskDescription.asStateFlow()

    // Initialization block to load the tasks list
    init {
        loadTasksList()
    }

    // Function to load the tasks list
    private fun loadTasksList() {
        viewModelScope.launch {
            _taskList.value = getAllTasksUseCase()
            _taskListCompleted.value = getAllTasksUseCase()
            _taskList.value = _taskList.value.filter { !it.taskCompleted }.toMutableList()
            _taskListCompleted.value = _taskListCompleted.value.filter { it.taskCompleted }.toMutableList()
        }
    }

    // Function to change the task name error text
    fun changeTaskNameError(newValue: String) {
        _taskNameErrorText.value = newValue
    }

    // Function to validate the task name
    private fun validateTaskName(name: String) {
        _taskNameErrorText.value = when {
            name.isEmpty() -> "This field cannot be empty"
            name.length > 25 -> "Max 25 characters allowed"
            else -> ""
        }
    }

    // Function to update the task when it is marked as complete
    fun updateTaskOnComplete(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(taskCompleted = task.taskCompleted))
            loadTasksList()
        }
    }

    // Function to reset text field values
    fun resetTextFieldValues() {
        _taskName.value = ""
        _taskDescription.value = ""
        _taskPriority.value = TaskPriority.NO_PRIORITY
        _textAreaDescriptionVisible.value = false
        _buttonDescColor.value = Color(0xFF9E9E9E)
    }

    // Function to update chip selection on click
    fun updateChipOnClick(label: String) {
        _allChip.value = false
        _priorityChip.value = false

        when (label) {
            "all" -> _allChip.value = true
            "priority" -> _priorityChip.value = true
        }
    }

    // Function to filter the task list based on chip selection
    fun filterListOnChipClick(label: String) {
        when (label) {
            "all" -> loadTasksList()
            "priority" -> {
                _taskList.value = _taskList.value.sortedBy { task ->
                    when (task.taskPriority) {
                        TaskPriority.HIGH -> 0
                        TaskPriority.MEDIUM -> 1
                        TaskPriority.LOW -> 2
                        TaskPriority.NO_PRIORITY -> 3
                    }
                }.toMutableList()
                _taskListCompleted.value = _taskListCompleted.value.sortedBy { task ->
                    when (task.taskPriority) {
                        TaskPriority.HIGH -> 0
                        TaskPriority.MEDIUM -> 1
                        TaskPriority.LOW -> 2
                        TaskPriority.NO_PRIORITY -> 3
                    }
                }.toMutableList()
            }
        }
    }

    // Function to change the button description color
    fun changeButtonDescColor(newValue: Color) {
        _buttonDescColor.value = newValue
    }

    // Function to handle task name change
    fun onTaskNameChange(newValue: String) {
        _taskName.value = newValue
        validateTaskName(newValue)
    }

    // Function to change the visibility of the description text area
    fun changeTextAreaVisibility(newValue: Boolean) {
        _textAreaDescriptionVisible.value = newValue
    }

    // Function to change the task priority
    fun changeTaskPriority(newValue: TaskPriority) {
        _taskPriority.value = newValue
    }

    // Function to handle task description change
    fun onTaskDescriptionChange(newValue: String) {
        _taskDescription.value = newValue
    }

    // Function to handle the floating button add task click
    fun floatingButtonAddTaskOnClick(newValue: Boolean) {
        _showAddTaskDialog.value = newValue
    }

    // Function to handle the create task button click
    fun createTaskButtonOnClick() {
        if (_taskNameErrorText.value.isEmpty()) {
            viewModelScope.launch {
                createTaskUseCase.invoke(
                    Task(
                        taskId = 0,
                        taskName = _taskName.value,
                        taskDescription = _taskDescription.value,
                        taskCompleted = false,
                        taskPriority = _taskPriority.value
                    )
                )
                loadTasksList()
            }
            _showAddTaskDialog.value = false
            _taskName.value = ""
            _taskDescription.value = ""
        }
    }

    // Function to show the delete task dialog
    fun showTaskPopupDelete(show: Boolean) {
        _showDeleteTaskDialog.value = show
    }

    // Function to handle delete task on trash click
    fun deleteTaskOnTrashClick(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
            loadTasksList()
        }
    }

    // Function to assign a task to be deleted
    fun assignDeleteTask(taskToDelete: Task) {
        _deleteTask.value = taskToDelete
    }
}
