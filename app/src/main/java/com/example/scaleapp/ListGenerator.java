package com.example.scaleapp;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListGenerator {
    public String[] getTonicArray(Context context){
        return context.getResources().getStringArray(R.array.TonicArray);
    }
    public ArrayList<String> genNoModes(Context context){
        ArrayList<String> toPractice = new ArrayList<>();
        toPractice.addAll(Arrays.asList(getTonicArray(context)));
        Collections.shuffle(toPractice);
        return toPractice ;
    }

    public ArrayList<String> genSingleMode(Context context, int numScales){
        //Takes in the context of the app from the class calling it
        //Then generates and returns the list in a random order
        ArrayList<String> toPractice = new ArrayList<>();
        int numCheck =0;
        List<String> randHolder = genNoModes(context);
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

    public ArrayList<String> genMultiMode(Context context, ArrayList<String> modes, int numScales){
        ArrayList<String> holder = genSingleMode(context,numScales);
        ArrayList<String> toPractice = new ArrayList<>();
        int modeCheck=0;
            for(int i = 0;i<holder.size();i++){
                if(modeCheck==modes.size()){
                    modeCheck=0;
                }
                toPractice.add(holder.get(i)+" "+modes.get(modeCheck));
                modeCheck++;
            }
            return toPractice;
    }
}

