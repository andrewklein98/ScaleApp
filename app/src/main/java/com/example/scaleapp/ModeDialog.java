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
        final String[] modes = getContext().getResources().getStringArray(R.array.mode_array);
        ArrayList<Integer> pickedModes = new ArrayList();
        ArrayList<String> modesPicked = new ArrayList();
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
                        for (Integer pickedMode : pickedModes) {
                            modesPicked.add(modes[pickedMode]);
                        }
                        modeInterface.sendModes(modesPicked);
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        modeInterface = (ModeDialogInterface) context;

    }

    public interface ModeDialogInterface{
        void sendModes(ArrayList<String> sent);
    }


}
