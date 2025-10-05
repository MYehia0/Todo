package com.example.todo.ui.home.list

import com.example.todo.databases.model.Task

interface onTaskListener {
    fun onTaskClick (task: Task?);
}