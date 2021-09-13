package com.android.mviexample.domain

import com.android.mviexample.data.repository.TaskRepository

class GetAllTasksUseCase {

    private val repository = TaskRepository()

    suspend operator fun invoke() = repository.getAllTask()

}