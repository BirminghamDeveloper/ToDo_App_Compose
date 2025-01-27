package com.hashinology.todoapp.util

import androidx.datastore.preferences.core.Preferences

object Constants {
    const val DATABASE_TABLE = "todo_table"
    const val DATABASE_NAME = "todo_database"

    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val SPLASH_SCREEN = "splash_screen"
    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"


    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_Key = "sort_state"

    const val MAX_TITLE_LENGTH = 20
}