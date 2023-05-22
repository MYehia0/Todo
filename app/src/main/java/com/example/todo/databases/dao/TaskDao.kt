package com.example.todo.databases.dao

import androidx.room.*
import com.example.todo.databases.model.Task

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task:Task)

    @Delete
    fun deleteTask(task:Task)

    @Update
    fun updateTask(task:Task)

    @Query("select * from Task")
    fun getAllTasks():List<Task>

    @Query("select * from Task where date=:date")
    fun getTasksByDate(date:Long): List<Task>
}