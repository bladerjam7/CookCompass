package com.johnco.cookcompass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskList() {
    val taskViewModel: TaskViewModel = viewModel()

    val tasks = taskViewModel.tasks.collectAsState()

    var taskString by rememberSaveable { mutableStateOf("") }

    Column {
        Row {
            OutlinedTextField(
                value = taskString,
                onValueChange = { taskString = it },
                label = { Text("Enter task") },
            )

            Button(onClick = {
                taskViewModel.addTask(taskString)
            }
            ) {
                Text("Add")
            }
        }

        LazyColumn {
            item {
                OutlinedTextField(
                    value = taskString,
                    onValueChange = { taskString = it },
                    label = { Text("Enter task") },
                )

                Button(
                    onClick = {
                        taskViewModel.addTask(taskString)
                    },
                ) {
                    Text("Add")
                }
            }

            items(tasks.value.size) { it ->
                Text(tasks.value[it])
            }
        }
    }
}