package com.example.g_6m_1.view.main.adapter

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.g_6m_1.databinding.ItemTaskBinding
import com.example.g_6m_1.model.Task
import com.example.g_6m_1.viewmodel.TaskViewModel

class TaskAdapter(
    private var list: MutableList<Task>,
    private val viewModel: TaskViewModel
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position], position)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tasks: MutableList<Task>) {
        list.clear()
        list.addAll(tasks)
        Log.d("gg", list.toString())
        notifyDataSetChanged()
    }

    fun addTask(task: Task) {
        list.add(task)
        notifyItemInserted(list.size)
    }

    fun removeTask(position: Int) {
        if (list.size == 1){
            list.clear()
        }
        try {
            list.removeAt(position)
        } catch (_: Exception) {

        }
        notifyItemRemoved(position)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, position: Int) {
            with(binding) {
                tvTitle.text = task.title
                checkIsDone.isChecked = task.isCompleted

                checkIsDone.setOnClickListener {
                    if (checkIsDone.isChecked) {
                        task.isCompleted = true
                        val string = SpannableString(task.title)
                        string.setSpan(StrikethroughSpan(), 0, string.length, 0)
                        tvTitle.text = string
                        viewModel.updateTask(task, position)

                    } else {
                        task.isCompleted = false
                        tvTitle.text = task.title
                        viewModel.updateTask(task, position)
                    }
                }

                itemView.setOnLongClickListener {
                    viewModel.deleteTask(task)
                    updateList(viewModel.tasks.toMutableList())
                    return@setOnLongClickListener true
                }
            }


        }

    }
}