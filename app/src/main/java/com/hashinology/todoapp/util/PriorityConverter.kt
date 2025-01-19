package com.hashinology.todoapp.util

import androidx.room.TypeConverter
import com.hashinology.todoapp.data.models.Priority

class PriorityConverter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name // Converts enum to string
    }

    @TypeConverter
    fun toPriority(value: String): Priority {
        return try {
            Priority.valueOf(value.uppercase()) // Converts string to enum (case-insensitive)
        } catch (e: IllegalArgumentException) {
            Priority.NONE // Fallback to a default value
        }
    }
}
