package com.example.mycontacts.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mycontacts.models.contact.Contact

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_contacts.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE_CONTACT =
            "CREATE TABLE ${Contact.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Contact.COLUMN_NAME_CONTACT} TEXT," +
                    "${Contact.COLUMN_NAME_PHONE} TEXT," +
                    "${Contact.COLUMN_NAME_EMAIL} TEXT)"

        private const val SQL_DELETE_TABLE_CONTACT = "DROP TABLE IF EXISTS ${Contact.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_CONTACT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase (db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE_CONTACT)
    }
}
