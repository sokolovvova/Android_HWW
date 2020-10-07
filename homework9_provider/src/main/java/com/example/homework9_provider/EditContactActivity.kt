package com.example.homework9_provider

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework9_provider.db.ContactItem
import com.example.homework9_provider.db.ContactsDatabase
import kotlinx.android.synthetic.main.activity_edit_contact.*

class EditContactActivity : AppCompatActivity(), View.OnClickListener {
    private var isPhone = false
    private var id : Long = 0
    private lateinit var contact : ContactItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)
        setSupportActionBar(toolbar)
        id = intent.getLongExtra("id",1)
        contact = ContactsDatabase.getInstance(applicationContext).contactDao().getContactById(id)
        editText1.setText(contact.name)
        editText2.setText(contact.data)
        isPhone = contact.isPhone
        buttonArrow.setOnClickListener(this)
        buttonOk.setOnClickListener(this)
        removeButton.setOnClickListener(this)
        if (isPhone) editText2.setHint(R.string.hint2) else editText2.setHint(R.string.hint3)
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
                contact.isPhone = isPhone
                ContactsDatabase.getInstance(applicationContext).contactDao().updateContact(contact)
                finish()
            }
            R.id.removeButton -> {
                ContactsDatabase.getInstance(applicationContext).contactDao().delContactById(id)
                finish()
            }
        }
    }
}