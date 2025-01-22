package com.hashinology.todoapp.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hashinology.todoapp.ui.screens.task.TaskAppBar
import com.hashinology.todoapp.ui.screens.task.TaskScreen
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import com.hashinology.todoapp.util.Action
import com.hashinology.todoapp.util.Constants.TASK_SCREEN
import com.hashinology.todoapp.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navaBackStackEntry ->
        val taskId = navaBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
//            Log.d("updateTaskFields", selectedTask.toString())
//            Log.d("getSelectedtask", taskId.toString())
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}