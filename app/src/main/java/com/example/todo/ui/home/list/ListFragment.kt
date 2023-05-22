package com.example.todo.ui.home.list

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databases.MyDateBase
import com.example.todo.databases.model.Task
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.ui.home.Constants
import com.example.todo.ui.home.showTask
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class ListFragment:Fragment() {
    lateinit var binding: FragmentListBinding
    val currentDate = Calendar.getInstance()
    lateinit var tasksAdapter: TasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMillisZero()
        tasksAdapter = TasksAdapter(null)
        binding.taskRecycler.adapter = tasksAdapter
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if(selected){
                currentDate.set(Calendar.DAY_OF_MONTH,date.day)
                currentDate.set(Calendar.MONTH,date.month-1)
                currentDate.set(Calendar.YEAR,date.year)
                Log.e("Day",currentDate.get(Calendar.DAY_OF_MONTH).toString())
                Log.e("Month",currentDate.get(Calendar.MONTH).toString())
                Log.e("hour",currentDate.get(Calendar.HOUR).toString())
                Log.e("minute",currentDate.get(Calendar.MINUTE).toString())
                Log.e("second",currentDate.get(Calendar.SECOND).toString())
                Log.e("miliisecond",currentDate.get(Calendar.MILLISECOND).toString())
                loadTasksFromDatebase()
            }
        }
        binding.calendarView.selectedDate= CalendarDay.today()
        tasksAdapter.onDoneClickListener = object: onDoneClickListener{
            override fun onDoneClick(position: Int, task: Task?, item: ItemTaskBinding) {
                if (task?.isDone == false){
                    task?.isDone = true
                    item.itemDone.setImageResource(R.drawable.done)
                    item.itemDone.setBackgroundResource(R.color.transpairant)
                    item.itemTaskTitle.setTextColor(Color.parseColor("#61E757"))
                    item.seprator.setBackgroundResource(R.color.green)
                    Log.e("Done1","true "+task?.isDone)
                }
                else{
                    task?.isDone = false
                    item.itemDone.setImageResource(R.drawable.ic_check)
                    item.itemDone.setBackgroundResource(R.drawable.item_done)
                    item.itemTaskTitle.setTextColor(Color.parseColor("#5D9CEC"))
                    item.seprator.setBackgroundResource(R.color.blue)
                    Log.e("Done1","false "+task?.isDone)
                }
                Log.e("Done2",task?.isDone.toString())
                MyDateBase.getDateBase(requireActivity())
                    .TaskDao()
                    .updateTask(task!!)
            }
       }
        tasksAdapter.onDeleteListener = object: OnDeleteListener{
            override fun onDelete(position: Int, task: Task?) {
                MyDateBase.getDateBase(requireActivity())
                    .TaskDao()
                    .deleteTask(task!!)
                loadTasksFromDatebase()
            }
        }
        tasksAdapter.onTaskListener = object :onTaskListener{
            override fun onTaskClick(task: Task?) {
//                val intent = Intent(requireActivity(), showTask::class.java)
//                intent.putExtra(Constants.Task,task)
                showDialogTaskFragment(task)
            }
        }
    }

    fun showDialogTaskFragment(task: Task?){
        val showTaskFragment = showTask()
        showTaskFragment.task = task!!
        showTaskFragment.show(childFragmentManager,null)
    }


    fun setMillisZero(){
        currentDate.set(Calendar.HOUR,0)
        currentDate.set(Calendar.MINUTE,0)
        currentDate.set(Calendar.SECOND,0)
        currentDate.set(Calendar.MILLISECOND,0)
    }

    override fun onResume() {
        super.onResume()
        loadTasksFromDatebase()
    }

    fun loadTasksFromDatebase(){
        if(!isResumed){
            return
        }
        val tasks = MyDateBase.getDateBase(requireActivity()).TaskDao().getTasksByDate(currentDate.timeInMillis)
//        Log.e("Loaded",tasks.get(0).title.toString())
        tasksAdapter.changeTaskData(tasks)
    }

}

