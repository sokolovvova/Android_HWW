package com.example.homework5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework5.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ContactListAdapter.OnItemListener {
    private lateinit var contactListAdapter: ContactListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener(this)
        initRecyclerView()
        contactListAdapter.updateContactList(applicationContext)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        contactListAdapter.updateContactList(applicationContext)
    }
    override fun onClick(view: View) {
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        contactListAdapter = ContactListAdapter(this)
        recyclerView.adapter = contactListAdapter
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra("id",contactListAdapter.getId(position))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        contactListAdapter.updateContactList(applicationContext)
    }
}