package com.example.scaleapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Context extends AppCompatActivity {
    private static Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext =this;
    }
    public static Context getContext(){
        return mContext;
    }
}
