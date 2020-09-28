package com.example.homework5.db

import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_list")
    fun getAll() : Array<ContactItem>

    @Insert
    fun addContact(contactItem: ContactItem)

    @Delete
    fun deleteContact(contactItem: ContactItem)

    @Update
    fun updateContact(contactItem: ContactItem)

    @Query("SELECT * FROM contact_list WHERE id=:id")
    fun getContactById(id: Long) : ContactItem

    @Query("DELETE FROM contact_list WHERE id=:id")
    fun delContactById(id: Long)
}