package com.example.todolist.ui.screens.task_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuOpen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.ui.screens.task_list.TaskListViewModel

@Composable
fun DescriptionActivatorButton(taskListViewModel: TaskListViewModel) {

    val buttonDescColor by taskListViewModel.buttonDescColor.collectAsStateWithLifecycle()

    Button(
        modifier = Modifier
            .padding(2.5.dp)
            .wrapContentHeight()
            .wrapContentWidth(),
        shape = RoundedCornerShape(9.dp),
        onClick = {
            when (buttonDescColor) {
                Color(0xFF9E9E9E) -> {
                    taskListViewModel.changeButtonDescColor(Color(0xFF3684BD))
                    taskListViewModel.changeTextAreaVisibility(true)
                }

                Color(0xFF3684BD) -> {
                    taskListViewModel.changeButtonDescColor(Color(0xFF9E9E9E))
                    taskListViewModel.changeTextAreaVisibility(false)
                }
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = buttonDescColor)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.MenuOpen,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun TextAreaDescription(taskListViewModel: TaskListViewModel) {

    val description by taskListViewModel.taskDescription.collectAsStateWithLifecycle()

    TextField(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
        value = description,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { taskListViewModel.onTaskDescriptionChange(it) },
        maxLines = 4,
        minLines = 4,
        placeholder = {
            Text(text = "Add a description")
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}