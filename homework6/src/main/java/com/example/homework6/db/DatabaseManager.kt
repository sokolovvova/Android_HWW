package com.example.homework6.db

import android.content.Context
import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.repo.*

class DatabaseManager(context : Context) {
    private var contactsDatabase = ContactsDatabase.getInstance(context)
    private var dataRepository :DataRepository = RxDataRepository.getInstance((contactsDatabase))
    lateinit var adapter : ContactListAdapter
    private var createdRepositoryList = arrayListOf<DataRepository>()


    fun changeDataRepository(selected : AsyncMode){
        dataRepository = when(selected){
            AsyncMode.RX_JAVA ->{
                RxDataRepository.getInstance(contactsDatabase)
            }
            AsyncMode.COMP_FUTURE->{
                CompFutureRepository.getInstance(contactsDatabase)
            }
            AsyncMode.TPE_HANDLER->{
                ThreadPoolRepository.getInstance(contactsDatabase)
            }
        }
        if(dataRepository !in createdRepositoryList) createdRepositoryList.add(dataRepository)
    }

    fun updateRV(){
        dataRepository.updateRV(object : RepositoryListener<Int>{
            override fun onDataReceived(data: Int) {
                adapter.notifyDataSetChanged()
            }
        },adapter)
    }

    fun addContact(contact : ContactItem){
        dataRepository.addContact(object : RepositoryListener<Int>{
            override fun onDataReceived(data: Int) {
                adapter.notifyDataSetChanged()
            }
        },contact,adapter)
    }

    fun updateContact(contact : ContactItem){
        dataRepository.updateContact(object : RepositoryListener<Int>{
            override fun onDataReceived(data: Int) {
                adapter.notifyDataSetChanged()
            }
        },contact,adapter)
    }

    fun delContactById(id : Long){
        dataRepository.delContactById(object : RepositoryListener<Int>{
            override fun onDataReceived(data: Int) {
                adapter.notifyDataSetChanged()
            }
        },id,adapter)
    }

    fun close(){
        for(dr in createdRepositoryList) dr.close()
    }

    companion object{
        private var INSTANCE: DatabaseManager? = null
        fun getInstance(context : Context): DatabaseManager {
            if(INSTANCE == null){
                INSTANCE = DatabaseManager(context)
            }
            return INSTANCE!!
        }
    }
}