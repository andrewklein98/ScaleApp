package com.example.scaleapp;


import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import java.util.ArrayList;

public class ImageGeneration {
   private Bitmap keySignature;
   private final int dpiScale;
   private final Context context;

   public ImageGeneration(Context context){
       this.context = context;
       DisplayMetrics metrics = context.getResources().getDisplayMetrics();
       dpiScale = metrics.densityDpi;
   }


    public BitmapDrawable combineImages(String tonic, String mode){
        keySignature = getKeySignature(tonic,mode);
        //returns the bitmap as a BitmapDrawable which can be used in a view image
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(context.getResources(), keySignature);
        return mBitmapDrawable;
    }

    public Bitmap getKeySignature(String tonic,String mode){
        Bitmap keySignature;
       switch(mode){
           case "Lydian":
               keySignature = null;
               break;
           case "Major":
               keySignature= getMajorKeySignature(tonic);
               break;
           case "Mixolydian":
               keySignature = null;
               break;
           case "Dorian":
               keySignature = null;
               break;
           case "Minor":
               keySignature = null;
               break;
           case "Phrygian":
               keySignature = null;
               break;
           case "Locrian":
               keySignature = null;
               break;
               default:
           keySignature = null;
       }

       //all tonics must be capitalised
        return keySignature;
    }

    public Bitmap getMajorKeySignature(String tonic){
        Bitmap keySignature;
        switch (tonic){
            case "C":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.cmajor);
                break;
            case "G":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.gmajor);
                break;
            case "D":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dmajor);
                break;
            case "A":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.amajor);
                break;
            case "E":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.emajor);
                break;
            case "B":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bmajor);
                break;
            case "F#":
                keySignature =BitmapFactory.decodeResource(context.getResources(),R.drawable.fsharpmajor);
                break;
            case "Db":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dflatmajor);
                break;
            case "Ab":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.aflatmajor);
                break;
            case "Eb":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.eflatmajor);
                break;
            case "Bb":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bflatmajor);
                break;
            case "F":
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.fmajor);
                break;
            default:
                keySignature = null;
                break;
        }
        return keySignature;
    }

    public Bitmap getLydianKeySignature() {
        int[] tonicIndex = new int[12];
        int[] modeIndex = new int[8];
        String tonic  = "C";
        //we want a return of the key signature based on the tonic and the mode
        //arbitrarily lets start with the tonic, ie Clydian
        //lydian will have position 0 in the mode index,
        //C has position 1 in the tonic index
        //search through tonic array to find the index of the tonic
        //a given mode will shift the keysignature by a certain ammount, eg
        //Mixolydian will shift backwards 1 , eg C major adds 1 flat

    }
}
