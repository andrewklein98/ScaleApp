package com.example.scaleapp;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListGenerator {
    public ArrayList<String> genSingleMode(Context context){
        //Takes in the context of the app from the class calling it
        //Then generates and returns the list in a random order
        String[] holder = context.getResources().getStringArray(R.array.TonicArray);
        List<String> listTonic = Arrays.asList(holder);
        ArrayList<String> toPractice = new ArrayList<>(listTonic);
        Collections.shuffle(toPractice);
        return toPractice;
    }
}

