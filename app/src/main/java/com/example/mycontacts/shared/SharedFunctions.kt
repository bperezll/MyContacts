package com.example.mycontacts.shared

import androidx.appcompat.app.AppCompatDelegate
import com.example.mycontacts.databinding.ItemContactBinding
import com.example.mycontacts.models.contact.Contact

class SharedFunctions {

    // Theme applied on the application
    fun applyTheme(isDarkTheme: Boolean) {
        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun nameInitials(contact: Contact, binding: ItemContactBinding) {



        // Get first char of the first and the second word if any
        val firstNameInitial = contact.name.firstOrNull()?.toString() ?: ""
        val secondNameInitial = contact.name.split(" ").getOrNull(1)?.firstOrNull()?.toString() ?: ""
        val initials = "$firstNameInitial$secondNameInitial"
        binding.nameInitials.text = initials
    }
}
