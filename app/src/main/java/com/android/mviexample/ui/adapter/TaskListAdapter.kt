package com.android.mviexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mviexample.R
import com.android.mviexample.data.model.TaskModel
import com.android.mviexample.databinding.ItemTaskBinding

class TaskListAdapter(private var list: List<TaskModel>) : RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTaskBinding.bind(itemView)

        fun bind(task: TaskModel){
            binding.tvTitleAndTaskCompleted.text = "${task.title} - ${task.completed}"
            binding.tvIdAnUserId.text = "${task.id} - ${task.userId}"
        }
    }

}