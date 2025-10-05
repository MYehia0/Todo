package com.example.todo.ui.home.list

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.R
import com.example.todo.databases.model.Task
import com.example.todo.databinding.ItemTaskBinding
import com.zerobranch.layout.SwipeLayout

class TasksAdapter(var items: List<Task>?): Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }
    lateinit var onDeleteListener:OnDeleteListener
    lateinit var onDoneClickListener: onDoneClickListener
    lateinit var onTaskListener: onTaskListener
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemTaskTitle.text = items?.get(position)?.title
        holder.binding.itemTaskDescriotion.text = items?.get(position)?.description
        if(items?.get(position)?.isDone == true){
            holder.binding.itemDone.setImageResource(R.drawable.done)
            holder.binding.itemDone.setBackgroundResource(R.color.transpairant)
            holder.binding.itemTaskTitle.setTextColor(Color.parseColor("#61E757"))
            holder.binding.seprator.setBackgroundResource(R.color.green)
            Log.e("Done3","true")
        }else{
            holder.binding.itemDone.setImageResource(R.drawable.ic_check)
            holder.binding.itemDone.setBackgroundResource(R.drawable.item_done)
            holder.binding.itemTaskTitle.setTextColor(Color.parseColor("#5D9CEC"))
            holder.binding.seprator.setBackgroundResource(R.color.blue)
            Log.e("Done3","false")
        }
        holder.binding.itemDone.setOnClickListener {
            onDoneClickListener.onDoneClick(position, items?.get(position),holder.binding)
        }
        holder.binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener{
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.RIGHT) {
                    // was executed swipe to the right
                    holder.binding.rightView.setOnClickListener{
                        onDeleteListener.onDelete(position, items?.get(position))
                        holder.binding.swipeLayout.close()
                    }
                } else if (direction == SwipeLayout.LEFT) {
                    // was executed swipe to the left
                }
            }

            override fun onClose() {

            }
        })

        holder.binding.dragItem.setOnClickListener{
            onTaskListener.onTaskClick(items?.get(position))
        }

    }

    fun changeTaskData(itemsTaskList:List<Task>?){
        items = itemsTaskList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items?.size?:0

    class ViewHolder(val binding:ItemTaskBinding):androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root){

    }
}