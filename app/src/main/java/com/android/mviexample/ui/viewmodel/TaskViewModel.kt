package com.android.mviexample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mviexample.data.repository.TaskRepository
import com.android.mviexample.domain.GetAllTasksUseCase
import com.android.mviexample.ui.intent.TaskIntent
import com.android.mviexample.ui.state.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase
    ): ViewModel() {

    val userIntent = Channel<TaskIntent>(Channel.UNLIMITED)
    private val _taskState = MutableStateFlow<TaskState>(TaskState.Idle)

    val tasktate: StateFlow<TaskState>
        get() = _taskState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it) {
                    is TaskIntent.FetchTasks -> fetchTasks()
                }
            }
        }
    }

    private suspend fun fetchTasks(){
        viewModelScope.launch {
            _taskState.value = TaskState.Loading
            _taskState.value =
                try {
                    TaskState.LoadTasks(getAllTasksUseCase())
                } catch (e: Exception) {
                    TaskState.Error(e.message)
                }
        }
    }
}