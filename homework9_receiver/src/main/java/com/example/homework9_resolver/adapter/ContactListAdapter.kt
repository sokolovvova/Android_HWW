package com.example.homework9_resolver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework9_resolver.R
import com.example.homework9_resolver.ContactItem
import java.util.*

class ContactListAdapter : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private var contactList = ArrayList<ContactItem>()

    fun updateContactList(list: ArrayList<ContactItem>){
        contactList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon)
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val dataTextView: TextView = itemView.findViewById(R.id.data)
        fun bind(contact: ContactItem) {
            nameTextView.text = contact.name
            dataTextView.text = contact.data
            if (contact.isPhone) iconImageView.setImageResource(R.drawable.ic_baseline_contact_phone_24) else iconImageView.setImageResource(R.drawable.ic_baseline_contact_mail_24)
        }
    }
}
