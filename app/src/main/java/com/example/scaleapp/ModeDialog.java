package com.example.scaleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModeDialog  extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final String[] modes = getContext().getResources().getStringArray(R.array.mode_array);
        ArrayList<Integer> pickedModes = new ArrayList();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick Mode")
                .setMultiChoiceItems(modes,null, new DialogInterface.OnMultiChoiceClickListener() {
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
                        //returns the values of the modes we want I think, i'm not sure
                        //https://developer.android.com/guide/topics/ui/dialogs#java
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
        return builder.create();
    }


}
