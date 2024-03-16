package com.example.mycontacts.utils

// SessionManager.kt
import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    companion object {
        private const val PREF_NAME = "session_pref"
        private const val KEY_THEME = "theme"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var theme: Boolean
        get() = sharedPref.getBoolean(KEY_THEME, false)
        set(value) = sharedPref.edit().putBoolean(KEY_THEME, value).apply()
}
