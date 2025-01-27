package com.hashinology.todoapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hashinology.todoapp.ui.screens.splash.SplashScreen
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import com.hashinology.todoapp.util.Constants.SPLASH_SCREEN


fun NavGraphBuilder.splashComposable(
    navigateToTaskScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = SPLASH_SCREEN
    ) {
        SplashScreen(navigateToListScreen = navigateToTaskScreen)
    }
}