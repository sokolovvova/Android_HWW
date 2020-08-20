package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class IntentFactory {
    private Context context;
    private View view;

    public IntentFactory(Context context, View view){
        this.context=context;
        this.view=view;
    }

    public Intent getIntent(){
        Intent intent = new Intent(context,SecondActivity.class);
        int layout=R.layout.activity_main;
        switch(view.getId()){
            case R.id.button1_1:
                layout=R.layout.activity_task_1_1;
                break;
            case R.id.button1_2:
                layout=R.layout.activity_task_1_2;
                break;
            case R.id.button2_1:
                layout=R.layout.activity_task_2_1;
                break;
            case R.id.button2_2:
                layout=R.layout.activity_task_2_2;
                break;
            case R.id.button2_3:
                layout=R.layout.activity_task_2_3;
                break;
            case R.id.button3_1:
                layout=R.layout.activity_task_3_1;
                break;
            case R.id.button3_2:
                layout=R.layout.activity_task_3_2;
                break;
            case R.id.button3_3:
                layout=R.layout.activity_task_3_3;
                break;
            case R.id.button4_1:
                layout=R.layout.activity_task_4_1;
                break;
            case R.id.button4_2:
                layout=R.layout.activity_task_4_2;
                break;
            case R.id.button4_3:
                layout=R.layout.activity_task_4_3;
                break;
            case R.id.button5:
                layout=R.layout.activity_task_5;
                break;
        }
        intent.putExtra("intent",layout);
        return intent;
    }
}
