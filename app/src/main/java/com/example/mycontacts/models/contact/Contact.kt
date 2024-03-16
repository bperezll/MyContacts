package com.example.mycontacts.models.contact

import com.example.mycontacts.utils.DatabaseManager

class Contact (var id: Int, var name: String, var phone: String, var email: String) {

    companion object {
        const val TABLE_NAME = "Contacts"
        const val COLUMN_NAME_CONTACT = "name"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_EMAIL = "email"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CONTACT,
            COLUMN_NAME_PHONE,
            COLUMN_NAME_EMAIL,
        )
    }
}
