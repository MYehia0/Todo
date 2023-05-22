package com.example.todo.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.databases.dao.TaskDao
import com.example.todo.databases.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class MyDateBase : RoomDatabase() {
    abstract fun TaskDao():TaskDao

    companion object{
        private var myDateBase:MyDateBase?= null
        var Database_Name = "TasksDateBase"
        fun getDateBase(context:Context): MyDateBase {
            if(myDateBase==null){
                myDateBase = Room.databaseBuilder(
                    context, MyDateBase::class.java, Database_Name
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()// remove all datebase when changed,and initialize new database
                    .build()
            }
            return myDateBase!!
        }
    }

}