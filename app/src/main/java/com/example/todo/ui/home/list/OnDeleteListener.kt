package com.example.todo.ui.home.list

import com.example.todo.databases.model.Task

interface OnDeleteListener {
    fun onDelete (position:Int, task: Task?)
}