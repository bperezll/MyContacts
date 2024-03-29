package com.example.mycontacts.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontacts.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    // Companion object to use on intent put extra
    companion object {
        const val EXTRA_ID = "CONTACT_ID"
        const val EXTRA_NAME = "CONTACT_NAME"
        const val EXTRA_PHONE = "CONTACT_PHONE"
        const val EXTRA_EMAIL = "CONTACT_EMAIL"
    }

    private lateinit var binding: ActivityContactDetailBinding // View Binding declaration
    private var contactId:String? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

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
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }
    private fun initView() {

        // Get the ID of the contact, only intent needed
        contactId = intent.getStringExtra(EXTRA_ID)

        // Display contact fields
        val name = intent.getStringExtra(EXTRA_NAME)
        val phone = intent.getStringExtra(EXTRA_PHONE)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        binding.nameTextView.text = name
        binding.phoneTextView.text = phone
        binding.emailTextView.text = email

        // Set the menu title to the contact name
        supportActionBar?.title = binding.nameTextView.text

        nameInitials()
    }

    private fun nameInitials() {

        // Get first char of the first and the second word if any
        val firstNameInitial = binding.nameTextView.text.firstOrNull()?.toString() ?: ""
        val secondNameInitial = binding.nameTextView.text.split(" ").getOrNull(1)?.firstOrNull()?.toString() ?: ""
        val initials = "$firstNameInitial$secondNameInitial"
        binding.nameInitials.text = initials
    }
}
