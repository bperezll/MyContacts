package com.example.mycontacts.activities.home

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ActivityHomeBinding
import com.example.mycontacts.databinding.DialogAddContactBinding
import com.example.mycontacts.models.contact.Contact
import com.example.mycontacts.models.contact.ContactAdapter
import com.example.mycontacts.models.contact.ContactDAO

class HomeFunctions() {/*

    private lateinit var binding: ActivityHomeBinding // View Binding declaration
    private lateinit var contactBinding: DialogAddContactBinding // View Binding declaration
    private lateinit var contactAdapter: ContactAdapter // Adapter declaration
    private var contactList:MutableList<Contact> = mutableListOf() // Using Task as a List
    private lateinit var contactDAO: ContactDAO // ContactDao declaration

    private fun addContact() {

        // Add contact dialog creation
        val addContactDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val addContactBinding: DialogAddContactBinding = DialogAddContactBinding.inflate(layoutInflater)
        addContactDialog.setView(addContactBinding.root)

        // Text and fields shown on the dialog
        addContactDialog.setTitle(R.string.add_contact)
        addContactDialog.setIcon(R.drawable.ic_add_contact)

        // Add contact button
        addContactDialog.setPositiveButton(R.string.add_contact, null)

        // Cancel button
        addContactDialog.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }

        // Showing the dialog on pressing FAB
        val alertDialog: AlertDialog = addContactDialog.create()
        alertDialog.show()

        // Positive button Click Listener
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {

            // Field values
            val contactName = addContactBinding.contactNameEditText.editableText.toString()
            val contactPhone = addContactBinding.contactPhoneEditText.editableText.toString()
            val contactEmail = addContactBinding.contactEmailEditText.editableText.toString()

            //// Controlling that any of the fields are not empty (Not functional at all)
            //if (contactName.isNotEmpty() || contactPhone.isNotEmpty() || contactEmail.isNotEmpty()) {
            if (contactName.isNotEmpty()) {

                // Add any of the fields added
                val contact = Contact(-1, contactName, contactPhone, contactEmail)
                contactDAO.insert(contact)

                // Loading the data
                //loadData()
                contactList = contactDAO.findAll()
                contactAdapter.updateItems(contactList)

                //Toast.makeText(this, R.string.add_task_success_message, Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                addContactBinding.contactNameEditText.error = getString(R.string.contact_name_error)
            }
        }
    }*/
}