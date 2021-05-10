package com.example.scaleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button metButton;
    Button modeButton;
    boolean useMetronome = false;
    int numScales;
    EditText trueFalseText;
    EditText scaleText;
    ArrayList<String> modes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gets the Edit Text for the number of scales
        scaleText = (EditText) findViewById(R.id.nScales);
        //sets up the dropdown(spinner) with the values from the mode array
        Spinner spin = (Spinner) findViewById(R.id.modespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        //gets the button for the metronome on off
        metButton = (Button) findViewById(R.id.button);
        //creates the button to pick modes
        modeButton = (Button) findViewById(R.id.modeButton);
    }

    //on click for the use metronome button
    public void metronomeButton(View view) {
        //checks if the button has been clicked, if not set it to true
        //if it has been clicked set use metronome to false
        //use metronome is set to false as a default
        if (useMetronome) {
            useMetronome = false;
            metButton.setText("Use metronome");

        } else if (useMetronome == false) {
            useMetronome = true;
            metButton.setText("Don't use metronome");

        }
    }

    //the on click for the start practice button
    public void startPractice(View view) {
        //opens the practice activity as an intent
        numScales =getNumScalesFromBox();
        modes = getModesFromBox();
        Intent intent = new Intent(this, PracticingActivity.class);
        intent.putExtra("metronomeOn", useMetronome);
        intent.putExtra("numScales", numScales);
        intent.putExtra("modes",modes);
        startActivity(intent);
    }

    public int getNumScalesFromBox() {
        if (scaleText.getText().toString().equals("")) {
            //something that will stop the user from breaking everything
            //that will have to be in the startPractice box
            return 12;
        } else {
             return Integer.parseInt(scaleText.getText().toString());
        }
    }
    public ArrayList<String> getModesFromBox(){
        //currently just gets the modes from a list, not the box
        ArrayList<String> holder = new ArrayList<String>();
        holder.add("Lydian");
        holder.add("major");
        return holder;
    }

    public void openModeDialog(View view){
        ModeDialog modeDialog = new ModeDialog();
        modeDialog.show(getSupportFragmentManager(),"Pick Mode");
    }


}