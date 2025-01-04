package com.example.todolist.ui.screens.task_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolist.data.local.model.TaskPriority
import com.example.todolist.ui.screens.task_detail.TaskDetailViewModel

@Composable
fun FilterChipDetailOptions(taskDetailViewModel: TaskDetailViewModel){

    val noPriorityChip by taskDetailViewModel.noPriorityChip.collectAsStateWithLifecycle()
    val lowChip by taskDetailViewModel.lowChip.collectAsStateWithLifecycle()
    val mediumChip by taskDetailViewModel.mediumChip.collectAsStateWithLifecycle()
    val highChip by taskDetailViewModel.highChip.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            selected = noPriorityChip,
            onClick = {
                taskDetailViewModel.updateChipOnClick("no priority")
                taskDetailViewModel.updatePriorityTask(TaskPriority.NO_PRIORITY)
            },
            label = { Text(text = "No priority") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF9E9E9E),
            )
        )
        FilterChip(
            selected = lowChip,
            onClick = {
                taskDetailViewModel.updateChipOnClick("low")
                taskDetailViewModel.updatePriorityTask(TaskPriority.LOW)
            },
            label = { Text(text = "Low") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF6ED173)
            )
        )
        FilterChip(
            selected = mediumChip,
            onClick = {
                taskDetailViewModel.updateChipOnClick("medium")
                taskDetailViewModel.updatePriorityTask(TaskPriority.MEDIUM)
            },
            label = { Text(text = "Medium") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFFFFC107)
            )
        )
        FilterChip(
            selected = highChip,
            onClick = {
                taskDetailViewModel.updateChipOnClick("high")
                taskDetailViewModel.updatePriorityTask(TaskPriority.HIGH)
            },
            label = { Text(text = "High") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFFF44336)
            )
        )
    }
}