package com.example.scaleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ModeDialog.ModeDialogInterface {

    Button metButton;
    Button modeButton;
    ToggleButton endButton;
    boolean useMetronome = false;
    boolean useEndless = false;
    int numScales;
    EditText scaleText;
    public static final int REQUEST_CODE =1;
    ArrayList<String> modes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gets the Edit Text for the number of scales
        scaleText = (EditText) findViewById(R.id.nScales);
        //gets the button for the metronome on off
        metButton = (Button) findViewById(R.id.button);
        //creates the button to pick modes
        modeButton = (Button) findViewById(R.id.modeButton);
        //creates the button for endless mode
        endButton = findViewById(R.id.buttonEndless);
        endButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //endless is on and the text for number of scale is greyed out
                    endButton.setText("On");
                    scaleText.setHint("unavailable in endless mode");
                    scaleText.setEnabled(false);
                    useEndless = true;
                }
                else{
                    endButton.setText("Off");
                    scaleText.setHint("number of scales to practice");
                    scaleText.setEnabled(true);
                    useEndless=false;
                    //endless is off, and the text for number of scales is not greyed out
                }
            }
        });

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
        //first checks if the modes have been entered
        numScales =getNumScalesFromBox();
        modes = getModesFromBox();
        Intent intent = new Intent(this, PracticingActivity.class);
        intent.putExtra("metronomeOn", useMetronome);
        intent.putExtra("numScales", numScales);
        intent.putExtra("modes",modes);
        intent.putExtra("endless",useEndless);
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
        return modes;
    }

    public void openModeDialog(View view){

        ModeDialog modeDialog = new ModeDialog();
        modeDialog.show(getSupportFragmentManager(),"Pick Mode");
    }

    //this sets the modes to the selection from the dialog box
    //this is done by using an interface from the dialog class
    //tbh I don't quite understand how it it is done, I need to have a look at the logic
    @Override
    public void sendModes(ArrayList<String> sent) {
        modes = sent;
    }
}