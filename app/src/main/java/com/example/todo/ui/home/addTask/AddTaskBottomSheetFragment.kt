package com.example.todo.ui.home.addTask

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.databases.MyDateBase
import com.example.todo.databases.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheetFragment:BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    var currentDate = Calendar.getInstance()
    init {
        currentDate.set(Calendar.HOUR,0)
        currentDate.set(Calendar.MINUTE,0)
        currentDate.set(Calendar.SECOND,0)
        currentDate.set(Calendar.MILLISECOND,0)
    }
    private var Month = currentDate.get(Calendar.MONTH) + 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnSubmit.isEnabled = !(taskTitle.isNullOrBlank()&&taskDescription.isNullOrBlank())
        binding.textTaskDate.text = ""+currentDate.get(Calendar.DAY_OF_MONTH) + " / " + Month +" / " +currentDate.get(Calendar.YEAR)+""
        binding.textTaskDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnSubmit.setOnClickListener {
            addTask()
        }
    }

    var onDismissListener:OnDismissListener? = null
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss()
    }

    fun showTaskInsertedDialog(){
        val alertDialog = AlertDialog.Builder(activity)
            .setMessage(getString(R.string.task_inserted_message))
            .setPositiveButton(R.string.ok) { dialog, which ->
                dialog.dismiss()
                dismiss()
            }
        alertDialog.show()
    }

    fun validateEditText():Boolean{
        var flag = true
        val taskTitle = binding.editTextEnterYourTask.editText?.text.toString()
        val taskDescription = binding.editTextEnterDescription.editText?.text.toString()
        if(taskTitle.isNullOrBlank()){
            binding.editTextEnterYourTask.error = "Please enter your task. "
            flag = false
        }else{
            binding.editTextEnterYourTask.error = null
        }
        if(taskDescription.isNullOrBlank()){
            binding.editTextEnterDescription.error = "Please enter description. "
            flag = false
        }else{
            binding.editTextEnterDescription.error = null
        }
        return flag
    }

    private fun addTask() {
        if(!validateEditText()){
            return
        }
        val taskTitle = binding.editTextEnterYourTask.editText?.text.toString()
        val taskDescription = binding.editTextEnterDescription.editText?.text.toString()
        // insert in Datebase
        MyDateBase.getDateBase(requireActivity())
            .TaskDao()
            .insertTask(Task(
                title = taskTitle,
                description = taskDescription,
                date = currentDate.timeInMillis
            ))
        showTaskInsertedDialog()
    }

    fun showDatePicker() {
        val datePicker =  DatePickerDialog(requireActivity(),{
                    view, year, month, dayOfMonth ->
            currentDate.set(Calendar.YEAR,year)
            currentDate.set(Calendar.MONTH,month)
            currentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            Month = month + 1
//            Log.e("Month",Month.toString())
            binding.textTaskDate.text = ""+currentDate.get(Calendar.DAY_OF_MONTH) + " / " + Month +" / " +currentDate.get(Calendar.YEAR)+""
        },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

}