package com.example.todolist.ui.screens.task_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.local.model.Task
import com.example.todolist.ui.screens.task_detail.TaskDetailViewModel

@Composable
fun TaskNameTextField(currentTask: Task, taskDetailViewModel: TaskDetailViewModel){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        value = currentTask.taskName,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { taskDetailViewModel.onTaskNameChange(it) },
        textStyle = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        )
    )
}