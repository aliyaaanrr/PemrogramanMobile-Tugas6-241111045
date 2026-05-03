package com.example.daftardata

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class   DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "SafeWalk.db"
        private const val DATABASE_VERSION = 1

        // Table Contacts
        private const val TABLE_CONTACTS = "contacts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"

        private const val CREATE_TABLE_CONTACTS = (
            "CREATE TABLE $TABLE_CONTACTS (" +
            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_NAME TEXT, " +
            "$COLUMN_PHONE TEXT)"
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CONTACTS)
        
        // Insert default contacts
        insertDefaultContacts(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    private fun insertDefaultContacts(db: SQLiteDatabase?) {
        val defaultContacts = listOf(
            Pair("Ayah", "0812-3456-7890"),
            Pair("Mamah", "0812-3456-7891"),
            Pair("Kaka", "0812-3456-7892"),
            Pair("Adek", "0812-3456-7893")
        )

        for (contact in defaultContacts) {
            val values = ContentValues().apply {
                put(COLUMN_NAME, contact.first)
                put(COLUMN_PHONE, contact.second)
            }
            db?.insert(TABLE_CONTACTS, null, values)
        }
    }

    fun getAllContacts(): List<Item> {
        val contactList = mutableListOf<Item>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CONTACTS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
                contactList.add(Item(id, name, phone))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactList
    }

    fun addContact(name: String, phone: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PHONE, phone)
        }
        return db.insert(TABLE_CONTACTS, null, values)
    }
}
