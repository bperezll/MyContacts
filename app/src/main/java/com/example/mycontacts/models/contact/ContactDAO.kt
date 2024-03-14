package com.example.mycontacts.models.contact

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.mycontacts.models.category.CategoryDAO
import com.example.mycontacts.utils.DatabaseManager

class ContactDAO (val context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(contact: Contact): Contact {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Contact.COLUMN_NAME_CONTACT, contact.name)
        values.put(Contact.COLUMN_NAME_PHONE, contact.phone)
        values.put(Contact.COLUMN_NAME_EMAIL, contact.email)
        //values.put(Contact.COLUMN_NAME_CATEGORY, contact.category.id)

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
        //values.put(Contact.COLUMN_NAME_CATEGORY, contact.category.id)

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
            //val categoryId = cursor.getInt(cursor.getColumnIndex(Contact.COLUMN_NAME_CATEGORY))
            //Log.i("DATABASE", "$id -> Task: $taskName, Done: $done")

            //val category = CategoryDAO(context).find(categoryId)!!
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
            null //"${Contact.COLUMN_NAME_DONE} ASC"               // The sort order
        )

        val list: MutableList<Contact> = mutableListOf()

        while (cursor.moveToNext()) {
            val contactId = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val contactName = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_CONTACT))
            val phone = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_PHONE))
            val email = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME_EMAIL))
            //val categoryId = cursor.getInt(cursor.getColumnIndex(Contact.COLUMN_NAME_CATEGORY))
            //Log.i("DATABASE", "$id -> Task: $taskName, Done: $done")

            //val category = CategoryDAO(context).find(categoryId)!!
            val contact = Contact(contactId, contactName, phone, email)
            list.add(contact)
        }

        cursor.close()
        db.close()

        return list
    }

    /* De momento no me hace falta find by category
    @SuppressLint("Range")
    fun findAllByCategory(category: Category): List<Task> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,                 // The table to query
            Task.COLUMN_NAMES,     // The array of columns to return (pass null to get all)
            "${Task.COLUMN_NAME_CATEGORY} = ${category.id}",                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            "${Task.COLUMN_NAME_DONE} ASC"               // The sort order
        )

        val list: MutableList<Task> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Task.COLUMN_NAME_TASK))
            val done = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_NAME_DONE)) == 1
            val categoryId = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_NAME_CATEGORY))
            //Log.i("DATABASE", "$id -> Task: $taskName, Done: $done")

            val category = CategoryDAO(context).find(categoryId)!!
            val task = Task(id, taskName, done, category)
            list.add(task)
        }

        cursor.close()
        db.close()

        return list
    }

    @SuppressLint("Range")
    fun countByCategoryAndDone(category: Category): Int {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,                 // The table to query
            arrayOf("COUNT(*)"),     // The array of columns to return (pass null to get all)
            "${Task.COLUMN_NAME_CATEGORY} = ${category.id} AND ${Task.COLUMN_NAME_DONE} = false",                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        var count = -1
        if (cursor.moveToNext()) {
            count = cursor.getInt(0)
        }

        cursor.close()
        db.close()

        return count
    }*/

}