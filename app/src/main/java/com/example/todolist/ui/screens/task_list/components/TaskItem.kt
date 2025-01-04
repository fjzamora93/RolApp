package com.example.todolist.ui.screens.task_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.data.local.model.Task

@Composable
fun TaskItem(
    task: Task,
    onCompleteClick: (Task) -> Unit,
    onDeleteClick: (Task) -> Unit,
    modifier: Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (task.taskCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.6f
                        ),
                        shape = CircleShape
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ){
                        val newValue = !task.taskCompleted
                        onCompleteClick(task.copy(taskCompleted = newValue))
                    },
                contentAlignment = Alignment.Center
            ) {
                if (task.taskCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = task.taskName,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = if (task.taskCompleted) TextDecoration.LineThrough else TextDecoration.None,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp)
                    .background(
                        color = task.taskPriority.bgColor,
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(
                    text = task.taskPriority.displayName[0].uppercaseChar().toString(),
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    color = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .fillMaxHeight()
                    .clickable {
                        onDeleteClick(task)
                    }
                    .background(
                        color = Color(0xFFDC5858),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.RestoreFromTrash,
                        contentDescription = "trashIcon",
                        tint = Color.White
                    )

                    Text(
                        text = "Delete",
                        fontWeight = FontWeight.Light,
                        fontSize = 8.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}