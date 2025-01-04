package com.example.todolist.ui.screens.task_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.screens.task_list.TaskListViewModel

@Composable
fun AddTaskFloatingButton(taskListViewModel: TaskListViewModel){
    FloatingActionButton(
        modifier = Modifier
            .padding(bottom = 15.dp, end = 15.dp)
            .size(70.dp),
        onClick = { taskListViewModel.floatingButtonAddTaskOnClick(true) },
        shape = CircleShape,
        containerColor = Color(0xFF3684BD),
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 5.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp),
            imageVector = Icons.Rounded.Add,
            contentDescription = "Floating action button.",
            tint = Color.White
        )
    }
}