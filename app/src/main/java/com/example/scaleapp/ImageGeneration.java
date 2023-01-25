package com.example.scaleapp;


import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.DisplayMetrics;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

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
       switch(mode){
           case "Lydian":
               break;
           case "Major":
               getMajorKeySignature(tonic);
               break;
           case "Mixolydian":
               break;
           case "Dorian":
               break;
           case "Minor":
               break;
           case "Phrygian":
               break;
           case "Locrian":
               break;
       }
       Bitmap keysignature;
       //all tonics and the first letters of modes must be capitalised        return keysignature;
    }

    public Bitmap getMajorKeySignature(String tonic){
        Bitmap keysignature;
        switch (tonic){
            case "C":
                break;
            case "G":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.gmajor);
                break;
            case "D":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dmajor);
                break;
            case "A":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.amajor);
                break;
            case "E":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.emajor);
                break;
            case "B":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bmajor);
                break;
            case "FSharp":
                keysignature =BitmapFactory.decodeResource(context.getResources(),R.drawable.fsharpmajor);
            case "DFlat":
                break;
            case "AFlat":
                break;
            case "EFlat":
                break;
            case "BFlat":
                break;
            case "F":
                break;
            default:
                keysignature = null;
                break;
        }

    }


}
