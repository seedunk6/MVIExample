package com.android.mviexample.data.network

import android.util.Log
import com.android.mviexample.di.DependenciesModule
import com.android.mviexample.data.TaskMapper
import com.android.mviexample.data.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskService @Inject constructor(
    private val api: TaskApiClient,
    private val mapper: TaskMapper
){

    suspend fun listTasks(): List<TaskModel> {
        return withContext(Dispatchers.IO) {
            val response =  api.getAllTasks()

            if (response.isSuccessful) {
                mapper.getTaskModelByTaskResponse(response.body())
            } else {
                emptyList()
            }
        }


    }
}