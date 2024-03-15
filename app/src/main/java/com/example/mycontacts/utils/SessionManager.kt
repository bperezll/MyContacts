package com.example.mycontacts.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

// Study how to add the Settings Theme Switch as Shared Preference
class SessionManager (context: Context) {

    companion object {
        const val THEME_SWITCH = "THEME_SWITCH"
    }

    private var sharedPref: SharedPreferences? = null

    init {
        sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
    }

    fun setTheme (isChecked: Boolean) {
        val themeSwitch = sharedPref?.edit()
        if (themeSwitch != null) {
            ////themeSwitch.putBoolean().AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ////themeSwitch.putString(THEME_SWITCH, isChecked)
            themeSwitch.apply()
        }
    }

    fun getTheme ():String? {
        return sharedPref?.getString(THEME_SWITCH, null)
    }
}