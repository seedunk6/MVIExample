package com.android.mviexample.ui.intent

sealed class TaskIntent {

    object FetchTasks: TaskIntent()
}