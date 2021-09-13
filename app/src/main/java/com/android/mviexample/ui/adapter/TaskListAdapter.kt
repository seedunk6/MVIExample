package com.android.mviexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mviexample.R
import com.android.mviexample.databinding.ItemTaskBinding

class TaskListAdapter(private val images: List<String>) : RecyclerView.Adapter<TaskListAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTaskBinding.bind(itemView)

        fun bind(image: String){
        }
    }

}