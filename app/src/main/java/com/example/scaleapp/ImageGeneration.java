package com.example.scaleapp;


import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;

import java.lang.reflect.Array;
import java.util.Arrays;

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
        int tonicMode = CalculateTonicModeIndex(tonic,mode);
        keySignature= ConvertTonicModeIndexToKeySignature(tonicMode);
       //all tonics must be capitalised
        return keySignature;
    }

    public Bitmap ConvertTonicModeIndexToKeySignature(int tonicMode){
       //this takes in the tonicMode index, which will correlate to the tonic's position round the circle of fifths, adjusted for the mode
        Bitmap keySignature;
        //+1 compensates for the array index
        switch (tonicMode){
            case 0:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.fmajor);
            case 1:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.cmajor);
                break;
            case 2:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.gmajor);
                break;
            case 3:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dmajor);
                break;
            case 4:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.amajor);
                break;
            case 5:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.emajor);
                break;
            case 6:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bmajor);
                break;
            case 7:
                keySignature =BitmapFactory.decodeResource(context.getResources(),R.drawable.fsharpmajor);
                break;
            case 8:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dflatmajor);
                break;
            case 9:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.aflatmajor);
                break;
            case 10:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.eflatmajor);
                break;
            case 11:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bflatmajor);
                break;
            case 12:
                keySignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.fmajor);
                break;
            default:
                keySignature = null;
                break;
        }
        return keySignature;
    }

    public int CalculateTonicModeIndex(String tonic,String mode) {
        //get tonic index in array
        //then get mode, mode will shift it through the tonic array key signatures, keeping major as the default
        //shifts through the list of key signatures, treating C as 0 and lydian as 0
        //keys and modes are arranged in the cycle of fifths
        int tonicModeIndex;
        int tonicIndex ;
        int modeIndex;
        String[] tonicArray= context.getResources().getStringArray(R.array.TonicArray);
        String[]modeArray = context.getResources().getStringArray(R.array.mode_array);
        tonicIndex =Arrays.asList(tonicArray).indexOf(tonic);
        modeIndex =Arrays.asList(modeArray).indexOf(mode);
        if(modeIndex-tonicIndex<=1){
            tonicModeIndex = tonicIndex - modeIndex +2;
        }else{
            tonicModeIndex = 11 - modeIndex +tonicIndex;
        }

        return tonicModeIndex;
    }
}
