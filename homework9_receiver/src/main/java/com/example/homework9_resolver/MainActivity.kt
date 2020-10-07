package com.example.homework9_resolver

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework9_resolver.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var contactListAdapter: ContactListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener(this)
        initRecyclerView()
    }
    override fun onClick(view: View) {
        val cursor = contentResolver.query(Uri.parse("content://com.example.homework9_provider/contact_list"),null,null,null,null)
        if(cursor!=null) contactListAdapter.updateContactList(cursorToContactList(cursor))
    }

    private fun cursorToContactList(cursor: Cursor): ArrayList<ContactItem>{
        val list = ArrayList<ContactItem>()
        if (cursor.moveToFirst()) {
            do{
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val data = cursor.getString(cursor.getColumnIndex("data"))
                val isPhone = cursor.getInt(cursor.getColumnIndex("is_phone"))>0
                list.add(ContactItem(name,data,isPhone))
            }
                while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        contactListAdapter = ContactListAdapter()
        recyclerView.adapter = contactListAdapter
    }
}