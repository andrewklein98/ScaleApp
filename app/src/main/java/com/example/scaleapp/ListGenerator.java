package com.example.scaleapp;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListGenerator {
    public ArrayList<String> genSingleMode(){

        ArrayList<String> toPractice =(ArrayList<String>) Arrays.asList(Context.getContext().getResources().getStringArray(R.array.TonicArray));
        Collections.shuffle(toPractice);
        return toPractice;
    }
}

