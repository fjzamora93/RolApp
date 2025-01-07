package com.example.todolist.ui.screens.task_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.todolist.data.local.model.Task
import com.example.todolist.navigation.ScreensRoutes
import com.example.todolist.ui.screens.task_detail.TaskDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTaskDialogDetail(taskDetailViewModel: TaskDetailViewModel, currentTask: Task, navHostController: NavHostController){
    BasicAlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onDismissRequest = { taskDetailViewModel.showTaskPopupDelete(false) },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .height(130.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = AbsoluteAlignment.Right
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp)
                        .align(Alignment.Start),
                    text = "Do you really want to delete this task?",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 20.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .clickable { taskDetailViewModel.showTaskPopupDelete(false) },
                        text = "CANCEL",
                        color = Color(0xFF3684BD).copy(0.6f)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        modifier = Modifier
                            .clickable {
                                taskDetailViewModel.showTaskPopupDelete(false)
                                taskDetailViewModel.deleteTaskOnTrashClick(currentTask)
                                navHostController.navigate(ScreensRoutes.TaskListScreen.route) {
                                    popUpTo(ScreensRoutes.TaskDetailScreen.route) {
                                        inclusive = true
                                    }
                                }
                            },
                        text = "DELETE",
                        color = Color(0xFF3684BD)
                    )
                }
            }
        }
    }
}