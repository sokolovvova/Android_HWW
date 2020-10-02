package com.example.homework7

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()
        val serviceIntent = Intent(this, LoggerService::class.java)
        startButton.setOnClickListener {
            startService(serviceIntent)
        }
        stopButton.setOnClickListener {
            stopService(serviceIntent)
        }
        if (!preferences.contains("storage")) {
            val editor = preferences.edit()
            editor.putString("storage", "Internal").apply()
        }
        when (preferences.getString("storage", "")) {
            "Internal" -> {
                radioButton1.isChecked = true
            }
            "External" -> {
                radioButton2.isChecked = true
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val editor = preferences.edit()
            if (i == R.id.radioButton1) editor.putString("storage", "Internal")
            else editor.putString("storage", "External")
            editor.apply()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }
}
