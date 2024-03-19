package com.example.mycontacts.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontacts.databinding.ActivitySettingsBinding
import com.example.mycontacts.shared.SharedFunctions
import com.example.mycontacts.utils.SessionManager


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding // View Binding declaration
    private lateinit var session: SessionManager // Session Manager declaration

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.detail_menu, menu)

        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // This event will enable the back function to the button on press

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

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
