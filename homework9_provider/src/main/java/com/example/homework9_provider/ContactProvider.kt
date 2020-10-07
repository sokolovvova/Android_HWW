package com.example.homework9_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.homework9_provider.db.ContactDao
import com.example.homework9_provider.db.ContactsDatabase

class ContactProvider: ContentProvider() {
    private lateinit var contactDAO: ContactDao
    companion object{
        const val AUTHORITY = "com.example.homework9_provider"
        const val CONTACT_TABLE = "contact_list"
        const val CONTACTS = 1
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init{
        uriMatcher.addURI(AUTHORITY, CONTACT_TABLE,CONTACTS)
    }

    override fun onCreate(): Boolean {
        contactDAO = ContactsDatabase.getInstance(context!!).contactDao()
        return false
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor
        when(uriMatcher.match(uri)){
            CONTACTS->   {
                cursor = contactDAO.getCursorAll()
                cursor.setNotificationUri(context?.contentResolver,uri)
                return cursor
            }
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}