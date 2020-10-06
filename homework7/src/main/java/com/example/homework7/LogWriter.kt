package com.example.homework7

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.lang.Exception
import java.util.concurrent.Executors

class LogWriter {
    private val executor = Executors.newSingleThreadExecutor()
    fun writeLog(log: String, context: Context) {
        executor.execute {
            val preferences = context.getSharedPreferences("myPreferences", AppCompatActivity.MODE_PRIVATE)
            val saveDirectory =
                    if (preferences.getString("storage", "") == "Internal") context.filesDir
                    else context.getExternalFilesDir(null)
            Log.d("myLOG", actionToLog(log))
            val myDir = File(saveDirectory, "log.txt")
            myDir.createNewFile()
            try {
                myDir.appendText(log + "\n")
            } catch (e: Exception) {
                e.printStackTrace()
            } }
    }
    fun close(){
        executor.shutdown()
    }

    companion object {
        private var INSTANCE: LogWriter? = null
        fun getInstance(): LogWriter {
            if (INSTANCE == null) {
                INSTANCE = LogWriter()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}