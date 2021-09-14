package com.android.mviexample.ui.state

import com.android.mviexample.data.model.TaskModel

sealed class TaskState {

    object Idle: TaskState()
    object Loading: TaskState()
    data class LoadTasks(val list: List<TaskModel>): TaskState()
    data class Error(val error: String?): TaskState()
}