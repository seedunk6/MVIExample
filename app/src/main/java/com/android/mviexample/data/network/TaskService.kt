package com.android.mviexample.data.network

import com.android.mviexample.core.RetrofitHelper
import com.android.mviexample.data.TaskMapper
import com.android.mviexample.data.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val mapper = TaskMapper()

    suspend fun listTasks(): List<TaskModel> {

        return withContext(Dispatchers.IO) {
            val response =  retrofit.create(TaskApiClient::class.java).getAllTasks()

            if (response.isSuccessful) {
                mapper.getTaskModelByTaskResponse(response.body())
            } else {
                emptyList()
            }
        }


    }
}