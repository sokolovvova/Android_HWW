package com.example.homework6.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [ContactItem::class],version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao

    companion object{
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,ContactsDatabase::class.java, "contacts-database").build()
            }
            return INSTANCE!!
        }
    }
}