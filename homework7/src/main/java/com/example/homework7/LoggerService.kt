package com.example.homework7

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat

class LoggerService : Service() {
    private lateinit var br: BroadcastReceiver

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Logger")
                .setContentText("Collecting data...").build()
        startForeground(1, notification)
        br = MyBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.SCREEN_OFF")
        intentFilter.addAction("android.intent.action.SCREEN_ON")
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE")
        intentFilter.addAction("android.location.PROVIDERS_CHANGED")
        registerReceiver(br, intentFilter)
        super.onCreate()
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        stopForeground(true)
        LogWriter.getInstance().close()
        LogWriter.destroyInstance()
        super.onDestroy()
    }
}