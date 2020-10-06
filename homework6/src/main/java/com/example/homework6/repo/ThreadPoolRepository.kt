package com.example.homework6.repo

import android.os.Looper
import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.db.ContactItem
import com.example.homework6.db.ContactsDatabase
import java.util.concurrent.Executors

class ThreadPoolRepository(db: ContactsDatabase): DataRepository {
    private var database = db
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = android.os.Handler(Looper.getMainLooper())

    override fun close() {
        executor.shutdown()
    }

    override fun updateRV(listener: RepositoryListener<Int>, ad: ContactListAdapter) {
        executor.submit {
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
            handler.post{
                listener.onDataReceived(5)
            }
        }
    }

    override fun addContact(listener: RepositoryListener<Int>, contactItem: ContactItem, ad: ContactListAdapter) {
        executor.submit{
            database.contactDao().addContact(contactItem)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
            handler.post{
                listener.onDataReceived(5)
            }
        }
    }

    override fun delContactById(listener: RepositoryListener<Int>, id: Long, ad: ContactListAdapter) {
        executor.submit{
            database.contactDao().delContactById(id)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
            handler.post{
                listener.onDataReceived(5)
            }
        }
    }

    override fun updateContact(listener: RepositoryListener<Int>, contactItem: ContactItem, ad: ContactListAdapter) {
        executor.submit{
            database.contactDao().updateContact(contactItem)
            ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
            handler.post{
                listener.onDataReceived(5)
            }
        }
    }

    companion object{
        private var INSTANCE: ThreadPoolRepository? = null
        fun getInstance(db : ContactsDatabase): ThreadPoolRepository {
            if(INSTANCE == null){
                INSTANCE = ThreadPoolRepository(db)
            }
            return INSTANCE!!
        }
    }
}