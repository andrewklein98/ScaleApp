package com.example.scaleapp;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListGenerator {
    public ArrayList<String> genSingleMode(Context context, int numScales){
        //Takes in the context of the app from the class calling it
        //Then generates and returns the list in a random order
        ArrayList<String> toPractice = new ArrayList<>();
        int numCheck =0;
        String[] holder = context.getResources().getStringArray(R.array.TonicArray);
        List<String> randHolder = Arrays.asList(holder);
        //needs one that is for if nScales <12
        while(numCheck <numScales) {
            Collections.shuffle(randHolder);
            for (int i = 0; i < 12; i++) {
                toPractice.add(randHolder.get(i));
                numCheck++;
                if(numCheck==numScales){
                    break;
                }
            }
        }
        //randomises the order of the practicing scale
        Collections.shuffle(toPractice);
        return toPractice;
    }
}

