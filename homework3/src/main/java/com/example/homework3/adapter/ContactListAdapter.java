package com.example.homework3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homework3.R;
import com.example.homework3.data.Contact;
import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private OnItemListener myOnItemListener;

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    public ContactListAdapter(OnItemListener onItemListener){
        this.myOnItemListener = onItemListener;
    }

    public void modifyItem(int position, Contact contact){
        contactList.set(position,contact);
        notifyDataSetChanged();
    }
    public void deleteItem(int position){
        contactList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(Contact contact){
        contactList.add(contact);
        notifyDataSetChanged();
    }

    public String getItemName(int position){
        return contactList.get(position).getName();
    }
    public String getItemData(int position){
        return contactList.get(position).getData();
    }
    public boolean isPhone(int position){
        return contactList.get(position).isPhone();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_layout,parent,false);
        return new ContactViewHolder(view,myOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iconImageView;
        private TextView nameTextView;
        private TextView dataTextView;
        OnItemListener onItemListener;

        public ContactViewHolder(View itemView, OnItemListener onItemListener){
            super(itemView);
            iconImageView = itemView.findViewById(R.id.icon);
            nameTextView = itemView.findViewById(R.id.name);
            dataTextView = itemView.findViewById(R.id.data);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        public void bind(final Contact contact){
            nameTextView.setText(contact.getName());
            dataTextView.setText(contact.getData());
            if(contact.isPhone()) iconImageView.setImageResource(R.drawable.ic_baseline_contact_phone_24);
            else iconImageView.setImageResource(R.drawable.ic_baseline_contact_mail_24);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListener{
        void onItemClick(int position);
    }
}