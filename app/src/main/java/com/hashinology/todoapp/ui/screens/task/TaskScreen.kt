package com.hashinology.todoapp.ui.screens.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hashinology.todoapp.data.models.Priority
import com.hashinology.todoapp.data.models.ToDoTask
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import com.hashinology.todoapp.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val localContext = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = {action ->
                    if (action == Action.NO_ACTION){
                        navigateToListScreen(action)
                    }else{
                        if (sharedViewModel.validateFields()){
                            navigateToListScreen(action)
                        }else{
                            displayToast(context = localContext)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                },
                modifier = Modifier
                    .padding(it) // Respect the scaffold's padding
                    .padding(top = 16.dp) // Additional padding to move the content down
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields are Empty", Toast.LENGTH_SHORT).show()
}
