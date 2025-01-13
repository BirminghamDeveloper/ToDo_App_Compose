package com.hashinology.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hashinology.todoapp.navigation.destination.listComposable
import com.hashinology.todoapp.navigation.destination.taskComposable
import com.hashinology.todoapp.util.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
navController: NavHostController
){
    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = "list/-1"
    ){
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}