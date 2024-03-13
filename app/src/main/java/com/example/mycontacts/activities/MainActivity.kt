package com.example.mycontacts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mycontacts.R
import com.example.mycontacts.data.models.contact.Contact
import com.example.mycontacts.data.models.contact.ContactAdapter
import com.example.mycontacts.data.models.contact.ContactDAO
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.databinding.DialogAddContactBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // View Binding declaration
    private lateinit var contactAdapter: ContactAdapter // Adapter declaration
    private var contactList:MutableList<Contact> = mutableListOf() // Using Task as a List
    private lateinit var contactDAO: ContactDAO
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding initialization
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // FAB button start alert dialog to add contact

        binding.addContactFAB.setOnClickListener() {

            // Add contact dialog creation
            val addContactDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            val addContactBinding: DialogAddContactBinding = DialogAddContactBinding.inflate(layoutInflater)
            addContactDialog.setView(addContactBinding.root)

            // Delete task button
            addContactDialog.setPositiveButton(R.string.add_contact) { _, _ ->
                // Actions to delete the task
                val contact = contactList[position]
                contactDAO.insert(contact)
                contactAdapter.notifyDataSetChanged()
            }

            // Cancel button
            addContactDialog.setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }

            // Showing the dialog on pressing delete icon
            val alertDialog: AlertDialog = addContactDialog.create()
            alertDialog.show()
        }
    }
}