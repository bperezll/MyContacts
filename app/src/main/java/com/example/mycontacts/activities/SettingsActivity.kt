package com.example.mycontacts.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mycontacts.databinding.ActivitySettingsBinding
import com.example.mycontacts.shared.SharedFunctions
import com.example.mycontacts.utils.SessionManager


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding // View Binding declaration
    private lateinit var session: SessionManager // Session Manager declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding initialization
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Session Manager initialization
        session = SessionManager(this)

        setupThemeSwitch()
    }

    // Switching the theme
    private fun setupThemeSwitch() {
        binding.switchTheme.isChecked = session.theme

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            session.theme = isChecked
            SharedFunctions().applyTheme(isChecked)
        }
    }
}
