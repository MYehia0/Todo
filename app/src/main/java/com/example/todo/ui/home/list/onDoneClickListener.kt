package com.example.todo.ui.home.list

import com.example.todo.databases.model.Task
import com.example.todo.databinding.ItemTaskBinding

interface onDoneClickListener {
    fun onDoneClick (position:Int,task:Task?,item: ItemTaskBinding);
}
