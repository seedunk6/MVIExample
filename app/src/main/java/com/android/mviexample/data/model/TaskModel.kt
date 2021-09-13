package com.android.mviexample.data.model

data class TaskModel (
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)