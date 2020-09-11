package com.example.homework4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.customView);
        final SharedPreferences settings = getSharedPreferences(getString(R.string.preferences_file),MODE_PRIVATE);
        customView.setTouchActionListener(new CustomView.touchActionListener() {
            @Override
            public void onTouchDown(int x, int y) {
                customView.colorChanger(x,y);
                if(settings.getBoolean("snackbar",false)){
                    Snackbar.make(findViewById(R.id.imageView),"Нажаты координаты ["+x+";"+y+"]",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Нажаты координаты ["+x+";"+y+"]",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.fab).setOnClickListener(this);
        };
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("colors",customView.getColours());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        customView.setColours(savedInstanceState.getIntArray("colors"));
    }
}
