package com.example.scaleapp;

import android.widget.TextView;

public class GlobalVariables {
    private static GlobalVariables globVar = new GlobalVariables();
    private GlobalVariables(){};
    public static GlobalVariables getInstance(){
        return globVar;
    }
    public static boolean buttonOn;

    public void setViewText(TextView textView,boolean bool){
        if(bool){
            textView.setText("true");
        }
        else{
            textView.setText("false");
        }
    }
}
