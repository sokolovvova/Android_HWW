package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick1_1Button(View view) {
        Intent intent = new Intent(this, Task_1_1.class);
        startActivity(intent);
    }

    public void onClick1_2Button(View view) {
    }

    public void onClick1_3Button(View view) {
    }

    public void onClick2_1Button(View view) {
    }

    public void onClick2_2Button(View view) {
    }

    public void onClick2_3Button(View view) {
    }

    public void onClick3_1Button(View view) {
    }

    public void onClick3_2Button(View view) {
    }

    public void onClick3_3Button(View view) {
    }
}
