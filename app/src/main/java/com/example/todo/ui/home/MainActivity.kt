package com.example.todo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.FragmentSettingsBinding
import com.example.todo.ui.home.addTask.AddTaskBottomSheetFragment
import com.example.todo.ui.home.addTask.OnDismissListener
import com.example.todo.ui.home.list.ListFragment
import com.example.todo.ui.home.settings.SettingsFragment
import com.example.todo.ui.home.settings.onButtonThemeListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val listFragment = ListFragment()
    val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav
            .setOnItemSelectedListener{
                when (it.itemId) {
                    R.id.list1 -> {
                        binding.title.text = "To Do List"
                        showFragment(listFragment)
                    }
                    R.id.settings -> {
                        binding.title.text = "Settings"
                        showFragment(settingsFragment)
                    }
                }
                return@setOnItemSelectedListener true
            }
        binding.bottomNav.selectedItemId = R.id.list1
        binding.floatingAddTask.setOnClickListener{
            showAddTaskBottomSheetFragment()
        }
        settingsFragment.onButtonThemeListener = object : onButtonThemeListener{
            override fun onButtonClick(item: FragmentSettingsBinding) {
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    item.btnTheme.text = "Dark"
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }else{
                    item.btnTheme.text = "Light"
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                binding.bottomNav.selectedItemId = R.id.list1
            }
        }
    }

    fun showAddTaskBottomSheetFragment(){
        val addTaskBottomSheetFragment = AddTaskBottomSheetFragment()
        addTaskBottomSheetFragment.onDismissListener = object : OnDismissListener {
            override fun onDismiss() {
                listFragment.loadTasksFromDatebase()
            }
        }
        addTaskBottomSheetFragment.show(supportFragmentManager,null)
    }

    fun showFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}