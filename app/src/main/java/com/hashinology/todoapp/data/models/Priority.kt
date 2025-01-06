package com.hashinology.todoapp.data.models

import androidx.compose.ui.graphics.Color
import com.hashinology.todoapp.ui.theme.HighPriorityColor
import com.hashinology.todoapp.ui.theme.LowPriorityColor
import com.hashinology.todoapp.ui.theme.MediumPriorityColor
import com.hashinology.todoapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}