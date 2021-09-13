package com.android.mviexample.data.response

import com.google.gson.annotations.SerializedName

data class TaskResponse (
    @SerializedName("userId") val userId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)