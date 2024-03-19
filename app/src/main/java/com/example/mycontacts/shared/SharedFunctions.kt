package com.example.mycontacts.shared

import androidx.appcompat.app.AppCompatDelegate

class SharedFunctions {

    // Theme applied on the application
    fun applyTheme(isDarkTheme: Boolean) {
        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
