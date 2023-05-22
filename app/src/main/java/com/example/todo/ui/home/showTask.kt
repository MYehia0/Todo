package com.example.todo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo.databases.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.databinding.FragmentShowTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class showTask:BottomSheetDialogFragment() {

    lateinit var binding:FragmentShowTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowTaskBinding.inflate(inflater,container,false)

        return binding.root
    }
    lateinit var task: Task
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleFragmentTask.text = task.title
        binding.textTaskFragmentDescription.text = task.description

    }
}