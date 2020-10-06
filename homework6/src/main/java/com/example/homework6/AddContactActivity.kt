package com.example.homework6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.db.ContactItem
import com.example.homework6.db.DatabaseManager
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        setSupportActionBar(toolbar)
        rb1.isChecked = true
        buttonArrow.setOnClickListener(this)
        buttonOk.setOnClickListener(this)
        rb.setOnCheckedChangeListener { _, _ -> if (rb1.isChecked) editText2.setHint(R.string.hint2) else editText2.setHint(R.string.hint3) }
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
                DatabaseManager.getInstance(applicationContext).addContact(ContactItem(name,data,isPhone))
                finish()
            }
        }
    }
}