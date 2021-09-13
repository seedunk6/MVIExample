package com.android.mviexample.data.network

import com.android.mviexample.data.response.TaskResponse
import retrofit2.Response
import retrofit2.http.GET

interface TaskApiClient {
    @GET("todos")
    suspend fun getAllTasks(): Response<List<TaskResponse>>
}