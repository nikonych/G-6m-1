package com.example.g_6m_1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.g_6m_1.model.Task

class TaskViewModel : ViewModel() {

    private val _tasksList = mutableListOf<Task>()
    val tasks: MutableList<Task>
        get() = _tasksList

    fun insertTask(task: Task) {
        _tasksList.add(task)
    }

    fun deleteTask(task: Task) {
        _tasksList.remove(task)
    }

    fun updateTask(task: Task, position: Int) {
        _tasksList[position] = task
    }

}