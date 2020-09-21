package com.example.homework4;

import android.graphics.Color;

import java.util.Random;

public class Calculations {
    Random rnd;
    public Calculations() {
        rnd = new Random();
    }
    public int getRandomColor(){
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
    public int[] getRandomColorArray(int size){
        int[] array = new int[size];
        for(int i =0;i<size;i++){
            array[i] = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }
        return array;
    }
}
