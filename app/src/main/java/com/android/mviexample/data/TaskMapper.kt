package com.android.mviexample.data

import com.android.mviexample.data.model.TaskModel
import com.android.mviexample.data.response.TaskResponse

class TaskMapper {

    fun getTaskModelByTaskResponse(taskResponse: List<TaskResponse>?): List<TaskModel> {
        val result  = arrayListOf<TaskModel>()
        if (taskResponse != null) {
            for (item in taskResponse) {
                result.add(
                    TaskModel(
                        userId = item.userId,
                        id =  item.id,
                        title = item.title,
                        completed = item.completed
                    )
                )
            }
        }
        return result
    }
}