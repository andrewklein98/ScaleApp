package com.example.scaleapp;


import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.DisplayMetrics;

public class ImageGeneration {
   private Bitmap note ,clef;
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
        //the canvas just allows for drawing operation on the bitmaps, it is not the image itself
        //as such the creation is with the clef image, and then the notes are mapped over it
        Canvas canvas = new Canvas(clef);
        canvas.drawBitmap(note,getPosition("tonic","mode",8),null);
        canvas.drawBitmap(note,1800,600,null);
        canvas.drawBitmap(note,1400,100,null);
        //returns the bitmap as a BitmapDrawable which can be used in a view image
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(context.getResources(), clef);
        return mBitmapDrawable;
    }
    public Matrix getPosition(String tonic, String mode, int degree){
        //this will be used multiple times for getting the position, which will based on the parameters
        //as well as the pixel density of the phone
        //pretty sure that that can just be a simple mathematical calculation
        //once I've sorted out the image sizes etc
        Matrix position = new Matrix();
        position.setTranslate(800,775);
        return position;
    }

    //public BitmapDrawable genKeySignatureBitMap(String tonic, String mode){
        // TODO: 21/06/2021 this should take in the tonic and mode, and then return the relevant key signature
      //  BitmapDrawable keySignature;


    //  }


}
