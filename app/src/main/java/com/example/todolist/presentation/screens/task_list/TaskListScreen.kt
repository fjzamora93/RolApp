package com.example.todolist.presentation.screens.task_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.todolist.navigation.Screens
import com.example.todolist.presentation.screens.generic_components.ProgressBarTask
import com.example.todolist.presentation.screens.task_list.components.AddTaskBottomSheet
import com.example.todolist.presentation.screens.task_list.components.AddTaskFloatingButton
import com.example.todolist.presentation.screens.task_list.components.DeleteTaskDialog
import com.example.todolist.presentation.screens.task_list.components.FilterChipOptions
import com.example.todolist.presentation.screens.task_list.components.TaskItem
import com.example.todolist.presentation.screens.task_list.model.TaskListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navHostController: NavHostController
) {
    val taskListViewModel: TaskListViewModel = hiltViewModel()


    val showAddTaskDialog by taskListViewModel.showAddTaskDialog.collectAsStateWithLifecycle()
    val showTaskPopupDelete by taskListViewModel.showDeleteTaskDialog.collectAsStateWithLifecycle()
    val deletedTask by taskListViewModel.deleteTask.collectAsStateWithLifecycle()

    val taskListUiState by taskListViewModel.uiState.collectAsStateWithLifecycle()

    if (showAddTaskDialog) {
        AddTaskBottomSheet(taskListViewModel = taskListViewModel)
    }
    if (showTaskPopupDelete) {
        DeleteTaskDialog(taskListViewModel, deletedTask)
    }

    when (taskListUiState) {
        is TaskListUiState.LOADING -> {
            ProgressBarTask(text = "Loading tasks")
        }

        is TaskListUiState.EMPTY -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { FilterChipOptions(taskListViewModel) },
                        colors = TopAppBarDefaults.topAppBarColors(Color.White)
                    )
                },
                containerColor = Color.White,
                floatingActionButton = {
                    AddTaskFloatingButton(taskListViewModel = taskListViewModel)
                },
                floatingActionButtonPosition = FabPosition.End,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = Icons.AutoMirrored.Rounded.List,
                        contentDescription = null,
                        tint = Color.LightGray,

                        )
                    Text(
                        text = "No tasks found",
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
                }
            }
        }

        is TaskListUiState.SUCCESS -> {
            TaskListContent(taskListViewModel, navHostController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TaskListContent(taskListViewModel: TaskListViewModel, navHostController: NavHostController) {

    val interactionSource = remember { MutableInteractionSource() }

    var isVisibleFirst by remember { mutableStateOf(true) }
    var isVisibleSecond by remember { mutableStateOf(true) }
    var isVisibleIconFirst by remember { mutableStateOf(Icons.Rounded.KeyboardArrowDown) }
    var isVisibleIconSecond by remember { mutableStateOf(Icons.Rounded.KeyboardArrowDown) }

    val taskList by taskListViewModel.taskList.collectAsStateWithLifecycle()
    val taskListCompleted by taskListViewModel.taskListCompleted.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { FilterChipOptions(taskListViewModel) },
                colors = TopAppBarDefaults.topAppBarColors(Color.White)
            )
        },
        containerColor = Color.White,
        floatingActionButton = {
            AddTaskFloatingButton(taskListViewModel = taskListViewModel)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 4.dp),
            state = rememberLazyListState()
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pending task",
                        modifier = Modifier.padding(start = 12.dp, top = 10.dp, bottom = 10.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = isVisibleIconFirst,
                        contentDescription = "dropDown",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                isVisibleIconFirst =
                                    if (isVisibleIconFirst == Icons.Rounded.KeyboardArrowDown) {
                                        Icons.Rounded.KeyboardArrowUp
                                    } else {
                                        Icons.Rounded.KeyboardArrowDown
                                    }
                                isVisibleFirst = !isVisibleFirst
                            }
                    )
                }
            }
            items(taskList.size) { task ->
                AnimatedVisibility(
                    visible = isVisibleFirst,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TaskItem(
                        modifier = Modifier
                            .padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 3.dp,
                                bottom = 3.dp
                            )
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {
                                navHostController.navigate(
                                    Screens.TaskDetailScreen.createRoute(
                                        taskList[task].taskId
                                    )
                                )
                            }
                            .background(
                                color = Color(0xFFF3F3F3),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .animateItemPlacement(animationSpec = tween(600)),
                        task = taskList[task],
                        onCompleteClick = { completedTask ->
                            taskListViewModel.updateTaskOnComplete(completedTask)
                        },
                        onDeleteClick = { deletedTask ->
                            taskListViewModel.showTaskPopupDelete(true)
                            taskListViewModel.assignDeleteTask(deletedTask)
                        }
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Completed task",
                        modifier = Modifier.padding(start = 12.dp, top = 20.dp, bottom = 10.dp),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        imageVector = isVisibleIconSecond,
                        contentDescription = "dropDown",
                        tint = Color.Black,
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                isVisibleIconSecond =
                                    if (isVisibleIconSecond == Icons.Rounded.KeyboardArrowDown) {
                                        Icons.Rounded.KeyboardArrowUp
                                    } else {
                                        Icons.Rounded.KeyboardArrowDown
                                    }
                                isVisibleSecond = !isVisibleSecond
                            }
                            .padding(top = 10.dp),
                    )
                }
            }
            items(taskListCompleted.size) { task ->
                AnimatedVisibility(
                    visible = isVisibleSecond,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TaskItem(
                        modifier = Modifier
                            .padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 3.dp,
                                bottom = 3.dp
                            )
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {
                                navHostController.navigate(
                                    Screens.TaskDetailScreen.createRoute(
                                        taskListCompleted[task].taskId
                                    )
                                )
                            }
                            .background(
                                color = Color(0xFFF3F3F3),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .animateItemPlacement(animationSpec = tween(600)),
                        task = taskListCompleted[task],
                        onCompleteClick = { completedTask ->
                            taskListViewModel.updateTaskOnComplete(completedTask)
                        },
                        onDeleteClick = { deletedTask ->
                            taskListViewModel.showTaskPopupDelete(true)
                            taskListViewModel.assignDeleteTask(deletedTask)
                        }
                    )
                }
            }

        }
    }
}
