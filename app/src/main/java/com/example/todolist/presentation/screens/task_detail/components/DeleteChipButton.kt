package com.example.todolist.presentation.screens.task_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RestoreFromTrash
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.presentation.screens.task_detail.TaskDetailViewModel

@Composable
fun DeleteChipButton(taskDetailViewModel: TaskDetailViewModel){
    Button(
        modifier = Modifier
            .padding(start = 12.dp)
            .width(150.dp),
        onClick = {
            taskDetailViewModel.showTaskPopupDelete(true)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFDC5858)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = Icons.Rounded.RestoreFromTrash,
                contentDescription = null,
                tint = Color.White,

                )
            Text(
                text = "Delete task",
                fontSize = 15.sp,
                color = Color.White
            )

        }
    }
}