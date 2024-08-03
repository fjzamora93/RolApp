package com.example.todolist.presentation.screens.task_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.presentation.screens.task_list.TaskListViewModel


@Composable
fun FilterChipOptions(taskListViewModel: TaskListViewModel) {

    val allChip by taskListViewModel.allChip.collectAsStateWithLifecycle()
    val priorityChip by taskListViewModel.priorityChip.collectAsStateWithLifecycle()

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedFilterChip(
            selected = allChip,
            onClick = {
                taskListViewModel.updateChipOnClick("all")
                taskListViewModel.filterListOnChipClick("all")
            },
            label = { Text(text = "All tasks") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF3684BD),
                selectedLabelColor = Color.White,
                containerColor = Color(0xFFF3F3F3),
                labelColor = Color.Black,
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        ElevatedFilterChip(
            selected = priorityChip,
            onClick = {
                taskListViewModel.updateChipOnClick("priority")
                taskListViewModel.filterListOnChipClick("priority")
            },
            label = { Text(text = "Priority") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF3684BD),
                selectedLabelColor = Color.White,
                containerColor = Color(0xFFF3F3F3),
                labelColor = Color.Black,
            )
        )
    }
}