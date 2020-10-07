package com.example.homework8.repository

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveDegreeType(isCelsius: Boolean) {
        preference.edit().putBoolean("isCelsius", isCelsius).apply()
    }

    fun getDegreeType(): Boolean {
        return preference.getBoolean("isCelsius", true)
    }
}