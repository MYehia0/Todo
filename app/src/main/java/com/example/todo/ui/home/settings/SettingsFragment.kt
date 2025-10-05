package com.example.todo.ui.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentSettingsBinding

class SettingsFragment:Fragment() {
    lateinit var binding: FragmentSettingsBinding
    lateinit var onButtonThemeListener: onButtonThemeListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            binding.btnTheme.text = "Light"
        }else{
            binding.btnTheme.text = "Dark"
        }
        binding.btnTheme.setOnClickListener{
//            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
//                binding.btnTheme.text = "Dark"
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }else{
//                binding.btnTheme.text = "Light"
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }
            onButtonThemeListener.onButtonClick(binding);
        }
    }

}