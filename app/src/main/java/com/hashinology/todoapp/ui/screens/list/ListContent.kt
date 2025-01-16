package com.hashinology.todoapp.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hashinology.todoapp.component.PriorityItem
import com.hashinology.todoapp.data.models.Priority
import com.hashinology.todoapp.data.models.ToDoTask
import com.hashinology.todoapp.ui.theme.LARGE_PADDING
import com.hashinology.todoapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.hashinology.todoapp.ui.theme.TASK_APP_BAR_HEIGHT
import com.hashinology.todoapp.ui.theme.ToDoAppTheme

@Composable
fun ListContent(
    
) {
    
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        shape = RectangleShape,
        shadowElevation = TASK_APP_BAR_HEIGHT,
        onClick = {navigateToTaskScreen(toDoTask.id)}
    ){
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row(){
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .weight(1f),
                   contentAlignment = Alignment.TopEnd
               ){
                   Canvas(modifier = Modifier
                       .width(PRIORITY_INDICATOR_SIZE)
                       .height(
                       PRIORITY_INDICATOR_SIZE)) {
                       drawCircle(
                           color = toDoTask.priority.color,
                       )
                   }
               }
            }
            Text(
                toDoTask.description,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    ToDoAppTheme {
        TaskItem(
            toDoTask = ToDoTask(0, "Title", "Random Text", Priority.MEDIUM),
            navigateToTaskScreen = {}
        )
    }
}