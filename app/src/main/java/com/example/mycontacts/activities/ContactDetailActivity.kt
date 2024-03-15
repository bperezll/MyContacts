package com.example.mycontacts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityContactDetailBinding
import com.example.mycontacts.databinding.ActivityHomeBinding
import com.example.mycontacts.models.contact.Contact
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
    private lateinit var contact:Contact // Contact model declaration
    private var contactId:String? = null //

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

        ////binding.superheroNameTextView.text = name

        findContactById(contactId!!)

    }

    private fun findContactById(contactId: String) {


        binding.nameTextView.text = contact.name
        binding.phoneTextView.text = contact.phone
        binding.emailTextView.text = contact.email
        //// Load the fields and options on the contact detail page. On development
        //binding.progress.visibility = View.GONE
        /*if (response.body() != null) {
            Log.i("HTTP", "respuesta correcta :)")
            superhero = response.body()!!
            binding.superheroNameTextView.text = superhero.name
            binding.superheroFirstAppearanceTextView.text = superhero.biography.firstAppearance
            binding.superheroPublisherTextView.text = superhero.biography.publisher
        } else {
            Log.i("HTTP", "respuesta erronea :(")
        }*/
    }
}