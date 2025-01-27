package com.hashinology.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hashinology.todoapp.navigation.destination.listComposable
import com.hashinology.todoapp.navigation.destination.splashComposable
import com.hashinology.todoapp.navigation.destination.taskComposable
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import com.hashinology.todoapp.util.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
navController: NavHostController,
sharedViewModel: SharedViewModel
){
    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
//        startDestination = "list/-1"
        startDestination = SPLASH_SCREEN
    ){
        splashComposable(
            navigateToTaskScreen = screen.splash,
            sharedViewModel
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }
}