package com.hashinology.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hashinology.todoapp.data.models.ToDoTask
import com.hashinology.todoapp.util.PriorityConverter

//@TypeConverters(PriorityConverter::class) // Register the converter here
@Database(
    entities = [ToDoTask::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
