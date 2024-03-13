package com.example.mycontacts.data.models.category

import com.example.mycontacts.utils.DatabaseManager

class Category (var id: Int, var category: String) {

    companion object {
        const val TABLE_NAME = "Categories"
        const val COLUMN_NAME_CATEGORY = "category"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CATEGORY
        )
    }

    /*override fun toString(): String {
        return "$id -> Task: $name - $color"
    }*/

    /*override fun equals(other: Any?): Boolean{
        if(other is Category){
            return id == other.id
        }
        return false;
    }*/
}