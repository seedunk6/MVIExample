package com.android.mviexample.domain

import com.android.mviexample.data.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepository
){
    suspend operator fun invoke() = repository.getAllTask()

}