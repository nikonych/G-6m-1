package com.example.g_6m_1.view.addTask

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.g_6m_1.R
import com.example.g_6m_1.databinding.FragmentAddTaskBinding
import com.example.g_6m_1.model.Task
import com.example.g_6m_1.viewmodel.TaskViewModel


class AddTaskFragment(
    val updateRecyclerView: (Task) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            val title = binding.etTaskTitle.text.toString()
            val task = Task(title=title, isCompleted = false)
            viewModel.insertTask(task)
            updateRecyclerView(task)
        }
    }


}