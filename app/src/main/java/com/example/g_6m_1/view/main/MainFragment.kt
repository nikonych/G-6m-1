package com.example.g_6m_1.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.g_6m_1.databinding.FragmentMainBinding
import com.example.g_6m_1.view.addTask.AddTaskFragment
import com.example.g_6m_1.view.main.adapter.TaskAdapter
import com.example.g_6m_1.viewmodel.TaskViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskAdapter = TaskAdapter(viewModel.tasks, viewModel)
        binding.recyclerTasks.adapter = taskAdapter
        initListener()
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            AddTaskFragment {
                taskAdapter.addTask(it)
            }.show(childFragmentManager, "gg")
        }
    }

}