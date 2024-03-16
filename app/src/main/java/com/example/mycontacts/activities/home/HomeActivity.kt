package com.example.mycontacts.activities.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.R
import com.example.mycontacts.activities.ContactDetailActivity
import com.example.mycontacts.activities.SettingsActivity
import com.example.mycontacts.models.contact.Contact
import com.example.mycontacts.models.contact.ContactAdapter
import com.example.mycontacts.models.contact.ContactDAO
import com.example.mycontacts.databinding.ActivityHomeBinding
import com.example.mycontacts.databinding.DialogAddContactBinding
import com.example.mycontacts.databinding.DialogDeleteContactBinding
import com.example.mycontacts.shared.SharedFunctions
import com.example.mycontacts.utils.SessionManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding // View Binding declaration
    private lateinit var contactAdapter: ContactAdapter // Adapter declaration
    private var contactList:MutableList<Contact> = mutableListOf() // Using Task as a List
    private lateinit var contactDAO: ContactDAO // ContactDao declaration
    private lateinit var session: SessionManager // Session Manager declaration

    // Showing the custom Home Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    // Button Settings make intent to Settings Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle item selection
        return when (item.itemId) {
            R.id.btnSettings -> {

                // Intent to go to Settings Activity
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateInitView()
    }

    private fun onCreateInitView() {
        // View binding initialization
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Session Manager initialization
        session = SessionManager(this)

        // Apply the saved theme
        SharedFunctions().applyTheme(session.theme)

        // ContactAdapter initialization
        contactAdapter = ContactAdapter(contactList, {
            onDeleteItemListener(it)
        }, {
            onItemClickListener(it)
        })

        // ContactDao initialization
        contactDAO = ContactDAO(this)

        // Display contacts on a linear layout
        binding.contactsRecyclerView.adapter = contactAdapter
        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)

        //// Make function to load de data
        contactList = contactDAO.findAll()
        contactAdapter.updateItems(contactList)

        searchContact()
        addContactDialog()
    }

    // Search contacts as you type
    private fun searchContact() {
        binding.contactSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean { return false }

            override fun onQueryTextChange(newText: String?): Boolean {

                contactList = if (newText.isNullOrEmpty()) {
                    contactDAO.findAll().toMutableList()
                } else {
                    contactDAO.findAll().filter { contact -> (contact.name).contains(newText, true) }.toMutableList()
                }

                // Update the list to all if empty, or to the ones containing the query
                contactAdapter.updateItems(contactList)
                return true
            }
        })
    }

    // FAB button start alert dialog to add contact
    private fun addContactDialog() {

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
        }
    }

    // Remove a contact with the delete button
    private fun onDeleteItemListener(position:Int) {

        // Remove contact dialog creation
        val askForDeleteDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val deleteMessageBinding: DialogDeleteContactBinding = DialogDeleteContactBinding.inflate(layoutInflater)
        askForDeleteDialog.setView(deleteMessageBinding.root)

        // Remove contact button
        askForDeleteDialog.setPositiveButton(R.string.remove_contact) { _, _ ->

            // Actions to delete the contact
            val contact = contactList[position]
            contactDAO.delete(contact)
            contactList.removeAt(position)
            contactAdapter.notifyDataSetChanged()
        }

        // Cancel button
        askForDeleteDialog.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }

        // Showing the dialog on pressing the trash icon
        val alertDialog: AlertDialog = askForDeleteDialog.create()
        alertDialog.show()
    }

    private fun onItemClickListener(position: Int) {

        // Assign to contact value the position on the list
        val contact: Contact = contactList[position]

        // Intent with elements displayed on Contact Detail Activity
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra("CONTACT_ID", contact.id)
        intent.putExtra("CONTACT_NAME", contact.name)
        intent.putExtra("CONTACT_PHONE", contact.phone)
        intent.putExtra("CONTACT_EMAIL", contact.email)
        startActivity(intent)
    }
}
