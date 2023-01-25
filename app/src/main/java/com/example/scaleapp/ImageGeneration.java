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
   private Bitmap note ,clef,sharp,flat,keySignature;
   private final int dpiScale;
   private final Context context;

   public ImageGeneration(Context context){
       this.context = context;
       DisplayMetrics metrics = context.getResources().getDisplayMetrics();
       dpiScale = metrics.densityDpi;
   }


    public BitmapDrawable combineImages(){
        //this allows the option for the bitmap being mutable
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable=true;
        //creates the bitmap for the combination
        note = BitmapFactory.decodeResource(context.getResources(),R.drawable.crotchet_resize,opt);
        clef = BitmapFactory.decodeResource(context.getResources(),R.drawable.treble_cleft_stave,opt);
        sharp = BitmapFactory.decodeResource(context.getResources(),R.drawable.sharp,opt);

        keySignature = getKeySignature("B","Major");
/*        canvas.drawBitmap(sharp,150,300,null);
        canvas.drawBitmap(note,1800,600,null);
        canvas.drawBitmap(note,1400,100,null);*/
        //returns the bitmap as a BitmapDrawable which can be used in a view image
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(context.getResources(), keySignature);
        return mBitmapDrawable;
    }

    public Bitmap getKeySignature(String tonic,String mode){
       Bitmap keysignature;
       //all tonics and the first letters of modes must be capitalised
        switch (tonic+mode){
            case "GMajor":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.gmajor);
                break;
            case "DMajor":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.dmajor);
            case "AMajor":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.amajor);
                break;
            case "EMajor":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.emajor);
                break;
            case "BMajor":
                keysignature = BitmapFactory.decodeResource(context.getResources(),R.drawable.bmajor);
                break;
            default:
                keysignature = null;
        }
        return keysignature;
    }



}
