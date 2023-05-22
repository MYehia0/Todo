package com.example.todo.ui.home.list

import com.example.todo.databases.model.Task
import com.example.todo.databinding.ItemTaskBinding

interface OnDeleteListener {
    fun onDelete (position:Int, task: Task?)
}