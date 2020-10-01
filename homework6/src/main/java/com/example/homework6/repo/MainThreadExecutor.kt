package com.example.homework6.repo

import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    private val handler = android.os.Handler(Looper.getMainLooper())
    override fun execute(p0: Runnable) {
        handler.post(p0)
    }
}