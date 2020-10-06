package com.example.homework6.repo

import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.db.ContactItem
import com.example.homework6.db.ContactsDatabase
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class CompFutureRepository(db: ContactsDatabase) : DataRepository {
    private var database = db
    private val executor = Executors.newSingleThreadExecutor()
    private val mainExecutor = MainThreadExecutor()
    override fun close() {
        executor.shutdown()
    }

    override fun updateRV(listener: RepositoryListener<Int>, ad: ContactListAdapter) {
        CompletableFuture.runAsync({
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
        },executor)
                .thenAcceptAsync({
                    listener.onDataReceived(5)
                },mainExecutor)
    }

    override fun addContact(listener: RepositoryListener<Int>, contactItem: ContactItem, ad: ContactListAdapter) {
        CompletableFuture.runAsync({
            database.contactDao().addContact(contactItem)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
        },executor)
                .thenAcceptAsync({
                    listener.onDataReceived(5)
                },mainExecutor)
    }

    override fun delContactById(listener: RepositoryListener<Int>, id: Long, ad: ContactListAdapter) {
        CompletableFuture.runAsync({
            database.contactDao().delContactById(id)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
        },executor)
                .thenAcceptAsync({
                    listener.onDataReceived(5)
                },mainExecutor)
    }

    override fun updateContact(listener: RepositoryListener<Int>, contactItem: ContactItem, ad: ContactListAdapter) {
        CompletableFuture.runAsync({
            database.contactDao().updateContact(contactItem)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
        },executor)
                .thenAcceptAsync({
                    listener.onDataReceived(5)
                },mainExecutor)
    }

    companion object{
        private var INSTANCE: CompFutureRepository? = null
        fun getInstance(db : ContactsDatabase): CompFutureRepository {
            if(INSTANCE == null){
                INSTANCE = CompFutureRepository(db)
            }
            return INSTANCE!!
        }
    }
}