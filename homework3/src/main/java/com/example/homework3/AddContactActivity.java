package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static android.text.InputType.TYPE_CLASS_PHONE;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rb1;
    private RadioGroup rb;
    private EditText editText1;
    private EditText editText2;
    private Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rb=findViewById(R.id.rb);
        rb1=findViewById(R.id.rb1);
        rb1.setChecked(true);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        findViewById(R.id.buttonArrow).setOnClickListener(this);
        findViewById(R.id.buttonOk).setOnClickListener(this);
        resultIntent = new Intent();
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rb1.isChecked()) editText2.setHint(R.string.hint2);
                else editText2.setHint(R.string.hint3);
            }
        });
    }

    @Override
    public void onClick(View view) {
        boolean isPhone;
        switch(view.getId()){
            case R.id.buttonArrow:
                resultIntent.putExtra("status",0);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
            case R.id.buttonOk:
                isPhone = rb1.isChecked();
                String name = editText1.getText().toString();
                String data = editText2.getText().toString();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("data", data);
                resultIntent.putExtra("isPhone", isPhone);
                setResult(RESULT_OK,resultIntent);
                finish();
                break;
    }
}
}