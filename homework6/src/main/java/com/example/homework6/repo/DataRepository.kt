package com.example.homework6.repo

import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.db.ContactItem

interface DataRepository {
    fun close()
    fun updateRV(listener: RepositoryListener<Int>,ad: ContactListAdapter)
    fun addContact(listener: RepositoryListener<Int>,contactItem: ContactItem,ad: ContactListAdapter)
    fun delContactById(listener: RepositoryListener<Int>,id: Long,ad: ContactListAdapter)
    fun updateContact(listener: RepositoryListener<Int>,contactItem: ContactItem,ad: ContactListAdapter)
}