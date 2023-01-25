package com.example.scaleapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListGenerator {
    public String[] getTonicArray(Context context){
        return context.getResources().getStringArray(R.array.TonicArray);
    }
    public ArrayList<String[]> genNoModes(Context context){
        //generates a shuffled arraylist of tonics
        //has an empty second array slot for consistency with the genMultiModesMethod
        ArrayList<String[]> toPractice = new ArrayList<>();
        ArrayList<String> tonicsHolder = new ArrayList<>();
        tonicsHolder.addAll(Arrays.asList(getTonicArray(context)));
        for(String tonic : tonicsHolder){
            String[] tonicAndMode = new String[]{tonic,null};
            toPractice.add(tonicAndMode);
        }
        Collections.shuffle(toPractice);
        return toPractice ;
    }

    public ArrayList<String> genSingleMode(Context context, int numScales){
        //Takes in the context of the app from the class calling it
        //Then generates and returns the list in a random order
        ArrayList<String> toPractice = new ArrayList<>();
        int numCheck =0;
        List<String> randHolder = Arrays.asList(getTonicArray(context));
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

    public ArrayList<String[]> genMultiMode(Context context, ArrayList<String> modes, int numScales){
        //returns an arralyist of arrays, with the tonic in position 0, and mode in position 1
        ArrayList<String> holder = genSingleMode(context,numScales);
        ArrayList<String[]> toPractice = new ArrayList<>();
        int modeCheck=0;
            for(int i = 0;i<holder.size();i++){
                if(modeCheck==modes.size()){
                    modeCheck=0;
                }
                String[] tonicModeArray = {holder.get(i),modes.get(modeCheck)};
                toPractice.add( tonicModeArray);
                modeCheck++;
            }
            return toPractice;
    }
}

