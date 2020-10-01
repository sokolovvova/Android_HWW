package com.example.homework6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework6.adapter.ContactListAdapter
import com.example.homework6.db.AsyncMode
import com.example.homework6.db.DatabaseManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ContactListAdapter.OnItemListener,AdapterView.OnItemSelectedListener {
    private lateinit var contactListAdapter: ContactListAdapter
    private lateinit var databaseManager: DatabaseManager
    private val paths = arrayListOf("ThreadPool","CompFuture","RxJava")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener(this)
        initRecyclerView()
        databaseManager = DatabaseManager.getInstance(applicationContext)
        databaseManager.adapter = contactListAdapter
        databaseManager.updateRV()

        val menuAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,paths)
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = menuAdapter
        spinner.onItemSelectedListener = this
        spinner.setSelection(2)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p2){
            0->{
                databaseManager.changeDataRepository(AsyncMode.TPE_HANDLER)
            }
            1->{
                databaseManager.changeDataRepository(AsyncMode.COMP_FUTURE)
            }
            2->{
                databaseManager.changeDataRepository(AsyncMode.RX_JAVA)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        databaseManager.updateRV()
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
        intent.putExtra(("name"),contactListAdapter.getName(position))
        intent.putExtra(("data"),contactListAdapter.getData(position))
        intent.putExtra(("isPhone"),contactListAdapter.isPhone(position))
        startActivity(intent)
    }

    override fun onResume() {
        databaseManager.updateRV()
        super.onResume()
    }

    override fun onDestroy() {
        databaseManager.close()
        super.onDestroy()
    }
}