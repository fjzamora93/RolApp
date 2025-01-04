package com.example.todolist.ui.screens.task_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.data.local.model.TaskPriority
import com.example.todolist.ui.screens.task_list.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(taskListViewModel: TaskListViewModel) {

    val taskName by taskListViewModel.taskName.collectAsStateWithLifecycle()
    val taskNameErrorMessage by taskListViewModel.taskNameErrorText.collectAsStateWithLifecycle()
    val textAreaDescriptionVisible by taskListViewModel.textAreaDescriptionVisible.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = {
            taskListViewModel.floatingButtonAddTaskOnClick(false)
            taskListViewModel.resetTextFieldValues()
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(10.dp),
                value = taskName,
                onValueChange = {
                    taskListViewModel.onTaskNameChange(it)
                },
                isError = taskNameErrorMessage.isNotEmpty(),
                singleLine = true,
                placeholder = { Text(text = "Enter a task name...") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                ),
            )
            if (taskNameErrorMessage.isNotEmpty()) {
                Text(
                    text = taskNameErrorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            if (textAreaDescriptionVisible) {
                TextAreaDescription(taskListViewModel = taskListViewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 10.dp, end = 10.dp, bottom = 15.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    PriorityButton(taskListViewModel)
                    DescriptionActivatorButton(taskListViewModel)
                }
                Button(
                    modifier = Modifier
                        .padding(2.5.dp)
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    shape = CircleShape,
                    onClick = { taskListViewModel.createTaskButtonOnClick() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3684BD))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PriorityButton(taskListViewModel: TaskListViewModel) {

    val currentPriority by taskListViewModel.taskPriority.collectAsStateWithLifecycle()

    Button(
        modifier = Modifier
            .padding(2.5.dp)
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(9.dp),
        onClick = {
            when (currentPriority) {
                TaskPriority.NO_PRIORITY -> taskListViewModel.changeTaskPriority(TaskPriority.LOW)
                TaskPriority.LOW -> taskListViewModel.changeTaskPriority(TaskPriority.MEDIUM)
                TaskPriority.MEDIUM -> taskListViewModel.changeTaskPriority(TaskPriority.HIGH)
                TaskPriority.HIGH -> taskListViewModel.changeTaskPriority(TaskPriority.NO_PRIORITY)
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = currentPriority.bgColor
        )
    ) {
        Text(text = currentPriority.displayName, color = Color.White)
    }
}
