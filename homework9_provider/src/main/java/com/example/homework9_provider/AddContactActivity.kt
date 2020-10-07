package com.example.homework9_provider

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework9_provider.db.ContactItem
import com.example.homework9_provider.db.ContactsDatabase
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        setSupportActionBar(toolbar)
        rb1.isChecked = true
        buttonArrow.setOnClickListener(this)
        buttonOk.setOnClickListener(this)
        rb.setOnCheckedChangeListener { radioGroup, i -> if (rb1.isChecked) editText2.setHint(R.string.hint2) else editText2.setHint(R.string.hint3) }
    }

    override fun onClick(view: View) {
        val isPhone: Boolean
        when (view.id) {
            R.id.buttonArrow -> {
                finish()
            }
            R.id.buttonOk -> {
                isPhone = rb1.isChecked
                val name = editText1.text.toString()
                val data = editText2.text.toString()
                ContactsDatabase.getInstance(applicationContext).contactDao().addContact(ContactItem(name,data,isPhone))
                finish()
            }
        }
    }
}