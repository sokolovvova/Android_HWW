package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb1;
    private RadioButton rb2;
    private SharedPreferences settings;
    private Button button;
    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = settings.edit();
        if(rb1.isChecked()) editor.putBoolean("snackbar",false);
        else editor.putBoolean("snackbar",true);
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(getString(R.string.preferences_file),MODE_PRIVATE);
        setContentView(R.layout.activity_setting);
        rb1= findViewById(R.id.rb1);
        rb2= findViewById(R.id.rb2);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        if(settings!=null){
            if(settings.getBoolean("snackbar",false)) rb2.setChecked(true);
            else rb1.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}