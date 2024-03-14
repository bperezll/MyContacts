package com.example.mycontacts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var contactDAO: ContactDAO // ContactDao declaration
    //private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding initialization
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ContactAdapter initialization
        contactAdapter = ContactAdapter(contactList) {
            contactAdapter.onClickListener(it)
        }

        // ContactDao initialization
        contactDAO = ContactDAO(this)

        binding.contactsRecyclerView.adapter = contactAdapter
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)

        // FAB button start alert dialog to add contact
        binding.addContactFAB.setOnClickListener {

            // Add contact dialog creation
            val addContactDialog: AlertDialog.Builder = AlertDialog.Builder(this)
            val addContactBinding: DialogAddContactBinding = DialogAddContactBinding.inflate(layoutInflater)
            addContactDialog.setView(addContactBinding.root)

            // Text and fields shown on the dialog
            addContactDialog.setTitle(R.string.add_contact)
            addContactDialog.setIcon(R.drawable.ic_add_contact)

            // Add contact button
            addContactDialog.setPositiveButton(R.string.add_contact, null)
            /*addContactDialog.setPositiveButton(R.string.add_contact) { _, _ ->
                // Actions to delete the task
                val contact = contactList[position]
                contactDAO.insert(contact)
                contactAdapter.notifyDataSetChanged()
            }*/

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


                if (contactName.isNotEmpty() || contactPhone.isNotEmpty() || contactEmail.isNotEmpty()) {
                    val contact = Contact(-1, contactName, contactPhone, contactEmail)
                    contactDAO.insert(contact)

                    // Loading the data
                    //loadData()
                    contactList = contactDAO.findAll()
                    contactAdapter.updateItems(contactList)

                    //Toast.makeText(this, R.string.add_task_success_message, Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                /*} else {
                    addContactBinding.taskTextField.error = getString(R.string.add_task_empty_error)*/
                }
            }
        }
    }
}