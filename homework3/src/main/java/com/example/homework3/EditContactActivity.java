package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class EditContactActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1;
    private EditText editText2;
    private Intent intent;
    private Intent resultIntent;
    private boolean isPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        intent = getIntent();
        editText1.setText(intent.getStringExtra("name"));
        editText2.setText(intent.getStringExtra("data"));
        findViewById(R.id.buttonArrow).setOnClickListener(this);
        findViewById(R.id.buttonOk).setOnClickListener(this);
        findViewById(R.id.removeButton).setOnClickListener(this);
        resultIntent = new Intent();
        isPhone = intent.getBooleanExtra("isPhone",false);
        if(isPhone) editText2.setHint(R.string.hint2);
        else editText2.setHint(R.string.hint3);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonArrow:
                resultIntent.putExtra("status",0);
                setResult(RESULT_CANCELED,resultIntent);
                finish();
                break;
            case R.id.buttonOk:
                String name = editText1.getText().toString();
                String data = editText2.getText().toString();
                resultIntent.putExtra("status",1);
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("data", data);
                resultIntent.putExtra("isPhone",isPhone);
                resultIntent.putExtra("position",intent.getIntExtra("position",-5));
                setResult(RESULT_OK,resultIntent);
                finish();
            case R.id.removeButton:
                resultIntent.putExtra("status",2);
                resultIntent.putExtra("position",intent.getIntExtra("position",-5));
                setResult(RESULT_OK,resultIntent);
                finish();
        }
    }
}