package com.example.homework7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val logField = intent.action?.let { actionToLog(it) }
        if (logField != null) {
            LogWriter.getInstance().writeLog(logField, context)
        }
    }
}