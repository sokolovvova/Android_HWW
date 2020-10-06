package com.example.homework6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.db.ContactItem
import com.example.homework6.db.DatabaseManager
import kotlinx.android.synthetic.main.activity_edit_contact.*

class EditContactActivity : AppCompatActivity(), View.OnClickListener {
    private var id : Long = 0
    private lateinit var contact : ContactItem
    private lateinit var databaseManager : DatabaseManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)
        setSupportActionBar(toolbar)
        databaseManager = DatabaseManager.getInstance(applicationContext)
        id = intent.getLongExtra("id",1)
        contact = ContactItem(intent.getStringExtra("name"),intent.getStringExtra("data"),intent.getBooleanExtra("isPhone",false))
        contact.id=id
        editText1.setText(contact.name)
        editText2.setText(contact.data)

        buttonArrow.setOnClickListener(this)
        buttonOk.setOnClickListener(this)
        removeButton.setOnClickListener(this)
        if (contact.isPhone) editText2.setHint(R.string.hint2) else editText2.setHint(R.string.hint3)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonArrow -> {
                finish()
            }
            R.id.buttonOk -> {
                val name = editText1.text.toString()
                val data = editText2.text.toString()
                contact.name = name
                contact.data = data
                databaseManager.updateContact(contact)
                finish()
            }
            R.id.removeButton -> {
                databaseManager.delContactById(id)
                finish()
            }
        }
    }
}