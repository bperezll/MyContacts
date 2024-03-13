package com.example.mycontacts.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.mycontacts.data.models.category.Category
import com.example.mycontacts.data.models.category.CategoryDAO
import com.example.mycontacts.data.models.contact.Contact

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private lateinit var categoryDAO: CategoryDAO
    companion object {
        const val DATABASE_NAME = "my_contacts.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE_CONTACT =
            "CREATE TABLE ${Contact.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Contact.COLUMN_NAME_CONTACT} TEXT," +
                    "${Contact.COLUMN_NAME_PHONE} TEXT," +
                    "${Contact.COLUMN_NAME_EMAIL} TEXT," +
                    "${Contact.COLUMN_NAME_CATEGORY} INTEGER," +
                    "CONSTRAINT fk_category " +
                    "FOREIGN KEY(${Contact.COLUMN_NAME_CATEGORY}) " +
                    "REFERENCES ${Category.TABLE_NAME}($COLUMN_NAME_ID))" // ON DELETE CASCADE)" De momento sin on delete cascade

        private const val SQL_DELETE_TABLE_CONTACT = "DROP TABLE IF EXISTS ${Contact.TABLE_NAME}"

        private const val SQL_CREATE_TABLE_CATEGORY =
            "CREATE TABLE ${Category.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Category.COLUMN_NAME_CATEGORY} TEXT)"

        private const val SQL_DELETE_TABLE_CATEGORY = "DROP TABLE IF EXISTS ${Category.TABLE_NAME}"
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;") // Creo que sin on delete cascade no hace falta este código
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_CATEGORY)
        db.execSQL(SQL_CREATE_TABLE_CONTACT)

        categoryDAO.insert(Category(0, "Family"))
        categoryDAO.insert(Category(1, "Friends"))
        categoryDAO.insert(Category(2, "Work"))
        categoryDAO.insert(Category(3, "Other"))

        //Log.i("DATABASE", "Table: ${Category.TABLE_NAME} Category: ${Category.COLUMN_NAME_CATEGORY} with ID: $COLUMN_NAME_ID")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase (db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE_CONTACT)
        db.execSQL(SQL_DELETE_TABLE_CATEGORY)
    }
}