package com.android.mviexample.data.repository

import com.android.mviexample.data.model.TaskModel
import com.android.mviexample.data.network.TaskService
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val api: TaskService){

    suspend fun getAllTask(): List<TaskModel> {
        return api.listTasks()
    }
}