package com.example.todo.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.todo.R
import com.example.todo.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) //when dark mode is enabled, we use the dark theme
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //when dark mode is enabled, we use the dark theme
        }
        setContentView(R.layout.activity_splash)
//        setTheme(R.style.Theme_Todo)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}