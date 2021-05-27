package com.example.scaleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Consumer;

public class ModeDialog  extends DialogFragment {
    ModeDialogInterface modeInterface;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //gets the mode list from the array in the resources
        final String[] modeArrayResource = getContext().getResources().getStringArray(R.array.mode_array);
        //adds the options for al modes, or tonics only in the array
        String[] modeArrayFull = populateFullModeArray(modeArrayResource);
        //the modes picked by the dialogue box
        ArrayList<Integer> pickedModes = new ArrayList();
        //the modes that will be sent to the main activity and then to the practicing activity
        ArrayList<String> modesToSend = new ArrayList();
        //calls the dialog builder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick Mode")
                .setMultiChoiceItems(modeArrayFull,null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int mode, boolean isChecked){
                        if(isChecked){
                            pickedModes.add(mode);
                        }else if(pickedModes.contains(mode)){
                            pickedModes.remove(mode);
                        }
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        for (Integer pickedMode : pickedModes) {
                            modesToSend.add(modeArrayFull[pickedMode]);
                        }
                        modeInterface.sendModes(modesToSend);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                })
                .setNeutralButton("All modes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        modeInterface = (ModeDialogInterface) context;

    }

    public interface ModeDialogInterface{
        void sendModes(ArrayList<String> sent);
    }

    public String[] populateFullModeArray(String[] modeArrayResource){
        int modeArrayLength = modeArrayResource.length;
        String[] modeArrayFull = new String[modeArrayLength+2];
        for(int i = 0;i<modeArrayLength;i++){
            modeArrayFull[i]=modeArrayResource[i];
        }
        int modeArrayFullLength = modeArrayFull.length;
        modeArrayFull[modeArrayFullLength-2]="Use All Modes";
        modeArrayFull[modeArrayFullLength-1]="Tonics Only";
        return modeArrayFull;
    }


}
