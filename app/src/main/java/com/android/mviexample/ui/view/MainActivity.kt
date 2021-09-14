package com.android.mviexample.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mviexample.data.model.TaskModel
import com.android.mviexample.databinding.ActivityMainBinding
import com.android.mviexample.ui.adapter.TaskListAdapter
import com.android.mviexample.ui.intent.TaskIntent
import com.android.mviexample.ui.state.TaskState
import com.android.mviexample.ui.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskListAdapter
    private val listTask = mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        onClickButton()
        observeViewModel()

    }

    private fun initRecycler() {
        taskAdapter = TaskListAdapter(listTask)
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.run {
            adapter = taskAdapter
        }

    }

    private fun onClickButton() {
        binding.btnTasks.setOnClickListener {
            lifecycleScope.launch {
                taskViewModel.userIntent.send(TaskIntent.FetchTasks)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            taskViewModel.tasktate.collect {taskState->
                when(taskState){
                    is TaskState.Loading -> binding.pbLoading.visibility = View.VISIBLE

                    is TaskState.LoadTasks -> {
                        binding.pbLoading.visibility = View.GONE
                        showList(taskState.list)
                    }

                    is TaskState.Error -> {
                        binding.rvTasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${taskState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showList(list: List<TaskModel>) {
        listTask.clear()
        listTask.addAll(list)
        taskAdapter.notifyDataSetChanged()
    }
}