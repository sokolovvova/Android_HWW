package com.example.homework9_provider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework9_provider.R
import com.example.homework9_provider.db.ContactItem
import com.example.homework9_provider.db.ContactsDatabase
import java.util.*

class ContactListAdapter(private val myOnItemListener: OnItemListener) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private var contactList = ArrayList<ContactItem>()

    fun updateContactList(context : Context){
        contactList = ContactsDatabase.getInstance(context).contactDao().getAll().toCollection(ArrayList<ContactItem>())
        notifyDataSetChanged()
    }

    fun getId(position: Int) : Long{
        return contactList[position].id
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_layout, parent, false)
        return ContactViewHolder(view, myOnItemListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View, onItemListener: OnItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon)
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val dataTextView: TextView = itemView.findViewById(R.id.data)
        var onItemListener: OnItemListener = onItemListener
        fun bind(contact: ContactItem) {
            nameTextView.text = contact.name
            dataTextView.text = contact.data
            if (contact.isPhone) iconImageView.setImageResource(R.drawable.ic_baseline_contact_phone_24) else iconImageView.setImageResource(R.drawable.ic_baseline_contact_mail_24)
        }
        override fun onClick(view: View) {
            onItemListener.onItemClick(adapterPosition)
        }
        init {
            itemView.setOnClickListener(this)
        }
    }
    interface OnItemListener {
        fun onItemClick(position: Int)
    }
}
