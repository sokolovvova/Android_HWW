package com.example.homework3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.homework3.adapter.ContactListAdapter;
import com.example.homework3.data.Contact;
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ContactListAdapter.OnItemListener  {

    private ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.fab).setOnClickListener(this);
        initRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("contactList",contactListAdapter.getContactList());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contactListAdapter.setContactList(savedInstanceState.<Contact>getParcelableArrayList("contactList"));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,AddContactActivity.class);
        startActivityForResult(intent,1);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(this);
        recyclerView.setAdapter(contactListAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,EditContactActivity.class);
        intent.putExtra("name",contactListAdapter.getItemName(position));
        intent.putExtra("data",contactListAdapter.getItemData(position));
        intent.putExtra("position",position);
        intent.putExtra("isPhone",contactListAdapter.isPhone(position));
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&& requestCode==0){
            if (data != null) {
                switch (data.getIntExtra("status",999)){
                    case 1:
                        contactListAdapter.modifyItem(data.getIntExtra("position",-5),new Contact(data.getStringExtra("name"),data.getStringExtra("data"),data.getBooleanExtra("isPhone",false)));
                        break;
                    case 2:
                        contactListAdapter.deleteItem(data.getIntExtra("position",-5));
                        break;
                }
            }
        }
        else if(resultCode==RESULT_OK&& requestCode==1){
            if (data != null) {
                contactListAdapter.addItem(new Contact(data.getStringExtra("name"),data.getStringExtra("data"),data.getBooleanExtra("isPhone",false)));
            }
        }
    }
}
