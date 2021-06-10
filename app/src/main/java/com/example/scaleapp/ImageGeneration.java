package com.example.scaleapp;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class ImageGeneration {
   private Bitmap note ,clef;

    private BitmapDrawable mBitmapDrawable;

    public BitmapDrawable combineImages(Context context){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable=true;
        note = BitmapFactory.decodeResource(context.getResources(),R.drawable.crotchet,opt);
        clef = BitmapFactory.decodeResource(context.getResources(),R.drawable.treble_cleft_stave,opt);
        Canvas canvas = new Canvas(clef);
        canvas.drawBitmap(note,500,-900,null);
        mBitmapDrawable = new BitmapDrawable(context.getResources(), clef);
        return mBitmapDrawable;
    }
}
