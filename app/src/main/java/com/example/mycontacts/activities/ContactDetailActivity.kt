package com.example.mycontacts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityContactDetailBinding
import com.example.mycontacts.databinding.ItemContactBinding
import com.example.mycontacts.models.contact.Contact
import com.example.mycontacts.models.contact.Contact.Companion.COLUMN_NAMES
import com.example.mycontacts.models.contact.ContactDAO
import com.example.mycontacts.shared.SharedFunctions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailActivity : AppCompatActivity() {

    // Companion object to use on intent put extra
    companion object {
        const val EXTRA_ID = "CONTACT_ID"
        const val EXTRA_NAME = "CONTACT_NAME"
        const val EXTRA_PHONE = "CONTACT_PHONE"
        const val EXTRA_EMAIL = "CONTACT_EMAIL"
    }

    private lateinit var binding: ActivityContactDetailBinding // View Binding declaration
    private lateinit var itemContactBinding: ItemContactBinding // View Binding declaration
    private lateinit var contact:Contact // Contact model declaration
    private var contactId:String? = null //

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
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }
    private fun initView() {

        ////contact.name = contact.toString()
        // Get the ID of the contact, only intent needed
        contactId = intent.getStringExtra(EXTRA_ID)

        //contact = Contact(contactId!!.toInt(), contact.name, contact.phone, contact.email)

        //val contactDAO = ContactDAO(this)
        //contact = contactDAO.find(contactId!!.toInt())!!

        //horoscope = HoroscopeProvider().getHoroscope(horoscopeId!!)
        //contact = mutableListOf(Contact).[contactId!!]

        // Display contact fields
        val name = intent.getStringExtra(EXTRA_NAME)
        val phone = intent.getStringExtra(EXTRA_PHONE)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        binding.nameTextView.text = name
        binding.phoneTextView.text = phone
        binding.emailTextView.text = email

        //SharedFunctions().nameInitials(contact, itemContactBinding)

        //contact = Contact(contactId!!, name, phone, email)

        //findContactById(contactId!!)

    }

    /*fun nameInitials() {

        contact =
        // Get first char of the first and the second word if any
        val firstNameInitial = contact.name.firstOrNull()?.toString() ?: ""
        val secondNameInitial =
            contact.name.split(" ").getOrNull(1)?.firstOrNull()?.toString() ?: ""
        val initials = "$firstNameInitial$secondNameInitial"
        binding.nameInitials.text = initials*/
    //}
}