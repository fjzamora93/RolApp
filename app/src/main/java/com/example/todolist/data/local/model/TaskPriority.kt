package com.example.todolist.data.local.model

import androidx.compose.ui.graphics.Color

enum class TaskPriority(val displayName: String, val bgColor: Color) {
    HIGH("High", Color(0xFFE53935)),
    MEDIUM("Medium", Color(0xFFFFC107)),
    LOW("Low", Color(0xFF6ED173)),
    NO_PRIORITY("No Priority", Color(0xFF9E9E9E))
}