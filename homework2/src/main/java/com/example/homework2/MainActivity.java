package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1_1).setOnClickListener(this);
        findViewById(R.id.button1_2).setOnClickListener(this);
        findViewById(R.id.button2_1).setOnClickListener(this);
        findViewById(R.id.button2_2).setOnClickListener(this);
        findViewById(R.id.button2_3).setOnClickListener(this);
        findViewById(R.id.button3_1).setOnClickListener(this);
        findViewById(R.id.button3_2).setOnClickListener(this);
        findViewById(R.id.button3_3).setOnClickListener(this);
        findViewById(R.id.button4_1).setOnClickListener(this);
        findViewById(R.id.button4_2).setOnClickListener(this);
        findViewById(R.id.button4_3).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        IntentFactory intentFactory = new IntentFactory(this,view);
        startActivity(intentFactory.getIntent());
    }
}
