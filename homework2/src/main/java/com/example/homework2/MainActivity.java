package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_1_1 = findViewById(R.id.button1_1);
        Button button_1_2 = findViewById(R.id.button1_2);
        Button button_2_1 = findViewById(R.id.button2_1);
        Button button_2_2 = findViewById(R.id.button2_2);
        Button button_2_3 = findViewById(R.id.button2_3);
        Button button_3_1 = findViewById(R.id.button3_1);
        Button button_3_2 = findViewById(R.id.button3_2);
        Button button_3_3 = findViewById(R.id.button3_3);
        Button button_4_1 = findViewById(R.id.button4_1);
        Button button_4_2 = findViewById(R.id.button4_2);
        Button button_4_3 = findViewById(R.id.button4_3);
        Button button_5 = findViewById(R.id.button5);

        button_1_1.setOnClickListener(this);
        button_1_2.setOnClickListener(this);
        button_2_1.setOnClickListener(this);
        button_2_2.setOnClickListener(this);
        button_2_3.setOnClickListener(this);
        button_3_1.setOnClickListener(this);
        button_3_2.setOnClickListener(this);
        button_3_3.setOnClickListener(this);
        button_4_1.setOnClickListener(this);
        button_4_2.setOnClickListener(this);
        button_4_3.setOnClickListener(this);
        button_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1_1:
                Intent intent1_1 = new Intent(this,Task_1_1.class);
                startActivity(intent1_1);
                break;

            case R.id.button1_2:
                Intent intent1_2 = new Intent(this,Task_1_2.class);
                startActivity(intent1_2);
                break;
            case R.id.button2_1:
                Intent intent2_1 = new Intent(this,Task_2_1.class);
                startActivity(intent2_1);
                break;

            case R.id.button2_2:
                Intent intent2_2 = new Intent(this,Task_2_2.class);
                startActivity(intent2_2);
                break;
            case R.id.button2_3:
                Intent intent2_3 = new Intent(this,Task_2_3.class);
                startActivity(intent2_3);
                break;
            case R.id.button3_1:
                Intent intent3_1 = new Intent(this,Task_3_1.class);
                startActivity(intent3_1);
                break;

            case R.id.button3_2:
                Intent intent3_2 = new Intent(this,Task_3_2.class);
                startActivity(intent3_2);
                break;
            case R.id.button3_3:
                Intent intent3_3 = new Intent(this,Task_3_3.class);
                startActivity(intent3_3);
                break;
            case R.id.button4_1:
                Intent intent4_1 = new Intent(this,Task_4_1.class);
                startActivity(intent4_1);
                break;

            case R.id.button4_2:
                Intent intent4_2 = new Intent(this,Task_4_2.class);
                startActivity(intent4_2);
                break;
            case R.id.button4_3:
                Intent intent4_3 = new Intent(this,Task_4_3.class);
                startActivity(intent4_3);
                break;

            case R.id.button5:
                Intent intent5 = new Intent(this,Task_5.class);
                startActivity(intent5);
                break;

        }
    }
}
