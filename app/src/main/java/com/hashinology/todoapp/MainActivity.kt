package com.hashinology.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hashinology.todoapp.navigation.SetUpNavigation
import com.hashinology.todoapp.ui.theme.ToDoAppTheme
import com.hashinology.todoapp.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoAppTheme {
                val sharedViewModel: SharedViewModel = hiltViewModel()
                navController = rememberNavController()
                SetUpNavigation(
                    navController,
                    sharedViewModel
                )
            }
        }
    }
}

