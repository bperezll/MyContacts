package com.example.mycontacts.models.contact

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.mycontacts.utils.DatabaseManager

class ContactDAO (val context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(contact: Contact): Contact {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Contact.COLUMN_NAME_CONTACT, contact.name)
        values.put(Contact.COLUMN_NAME_PHONE, contact.phone)
        values.put(Contact.COLUMN_NAME_EMAIL, contact.email)

        val newRowId = db.insert(Contact.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        contact.id = newRowId.toInt()
        return contact
    }

    fun update(contact: Contact) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Contact.COLUMN_NAME_CONTACT, contact.name)
        values.put(Contact.COLUMN_NAME_PHONE, contact.phone)
        values.put(Contact.COLUMN_NAME_EMAIL, contact.email)

        val updatedRows = db.update(Contact.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${contact.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(contact: Contact) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Contact.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${contact.id}", null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Contact? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Contact.TABLE_NAME,                         // The table to query
            Contact.COLUMN_NAMES,       // The array of columns to return (pass null to get all)
            "${DatabaseManager.COLUMN_NAME_ID} = $id",                        // The columns for the WHERE clause
            null,                    // The values for the WHERE clause
            null,                        // don't group the rows
            null,                         // don't filter by row groups
            null                         // The sort order
        )

        var contact: Contact? = null

        if (cursor.moveToNext()) {
            val contactId = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val contactName = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_CONTACT))
            val phone = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_PHONE))
            val email = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_EMAIL))

            contact = Contact(contactId, contactName, phone, email)
        }

        cursor.close()
        db.close()

        return contact
    }

    @SuppressLint("Range")
    fun findAll(): MutableList<Contact> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Contact.TABLE_NAME,                 // The table to query
            Contact.COLUMN_NAMES,     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            "${Contact.COLUMN_NAME_CONTACT} GLOB '[A-Za-z]*' DESC, ${Contact.COLUMN_NAME_CONTACT}" // Sort alphabetically
        )

        val list: MutableList<Contact> = mutableListOf()

        while (cursor.moveToNext()) {
            val contactId = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val contactName = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_CONTACT))
            val phone = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_PHONE))
            val email = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_EMAIL))

            val contact = Contact(contactId, contactName, phone, email)
            list.add(contact)
        }

        cursor.close()
        db.close()

        return list
    }
}
