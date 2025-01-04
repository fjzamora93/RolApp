package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.ui.screens.task_detail.TaskDetailScreen
import com.example.todolist.ui.screens.task_list.TaskListScreen

@Composable
fun NavGraph(
    startDestination: String,
    navHostController: NavHostController,
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {

        composable(Screens.TaskListScreen.route) {
            TaskListScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = Screens.TaskDetailScreen.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(
                taskId = taskId,
                navHostController = navHostController
            )
        }

    }
}