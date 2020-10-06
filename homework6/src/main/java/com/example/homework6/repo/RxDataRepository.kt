package com.example.homework6.repo

import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.db.ContactItem
import com.example.homework6.db.ContactsDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxDataRepository(db: ContactsDatabase) :DataRepository{
    private var database = db
    private var disposables = arrayListOf<Disposable>()

    override fun addContact(listener: RepositoryListener<Int>, contactItem: ContactItem,ad: ContactListAdapter) {
        disposables.add(Observable.just(contactItem)
                .subscribeOn(Schedulers.single())
                .doOnNext {
                    database.contactDao().addContact(contactItem)
                    ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onDataReceived(5)
                },{
                    error -> error.printStackTrace()
                }))
    }

    override fun delContactById(listener: RepositoryListener<Int>, id: Long,ad: ContactListAdapter) {
        disposables.add(Observable.just("")
                .subscribeOn(Schedulers.single())
                .doOnNext {
                    database.contactDao().delContactById(id)
                    ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onDataReceived(5)
                },{
                    error -> error.printStackTrace()
                }))
    }

    override fun updateContact(listener: RepositoryListener<Int>, contactItem: ContactItem,ad: ContactListAdapter) {
        disposables.add(Observable.just(contactItem)
                .subscribeOn(Schedulers.single())
                .doOnNext {
                    database.contactDao().updateContact(contactItem)
                    ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onDataReceived(5)
                },{
                    error -> error.printStackTrace()
                }))
    }

    override fun updateRV(listener: RepositoryListener<Int>,ad: ContactListAdapter) {
        disposables.add(Observable.just("")
                .subscribeOn(Schedulers.single())
                .doOnNext {
                    ad.contactList=database.contactDao().getAll().toCollection(ArrayList())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onDataReceived(5)
                },{
                    error -> error.printStackTrace()
                }))
    }

    companion object{
        private var INSTANCE: RxDataRepository? = null
        fun getInstance(db : ContactsDatabase): RxDataRepository {
            if(INSTANCE == null){
                INSTANCE = RxDataRepository(db)
            }
            return INSTANCE!!
        }
    }

    override fun close() {
        for(disposable in disposables) disposable.dispose()
    }
}