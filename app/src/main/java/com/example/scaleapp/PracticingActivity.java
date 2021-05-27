package com.example.scaleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PracticingActivity extends AppCompatActivity {

    TextView scaleBox;
    TextView tonicBox;

    ListGenerator listGen;

    ArrayList<String> tonics;
    ArrayList<String> modes;

    String lastScale;

    int numScales;
    int scaleIndex =0;

    boolean metronome;
    boolean endless;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicing);
        listGen = new ListGenerator();

        //gets the text box for the metronome field
        EditText metBox = (EditText) findViewById(R.id.metronomeBox);
        tonicBox = (TextView) findViewById(R.id.tonicBox);
        //gets the text boc for the number of scales
        scaleBox= (TextView) findViewById(R.id.numScaleText);
        //gets the values that we passed into the activity when it was created
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //gets all the values passed in from the previous activity
            metronome = bundle.getBoolean("metronomeOn");
            modes = bundle.getStringArrayList("modes");
            endless = bundle.getBoolean("endless");
            //checks if the metronome is on
            if(metronome){
                metBox.setText("Metronome On");
            }
            else {
                metBox.setText("Metronome Off");
            }
            //checks if endless mode is on, if so gets the numScales
            if(!endless) {
                numScales = bundle.getInt("numScales");
                scaleBox.setText(String.valueOf(numScales)  + " Scales Completed");
            }else{
                scaleBox.setText("0 scales completed");
            }
            //checks number of modes and endless, then gens the list
            if(modes==null &&!endless){
                tonics = listGen.genNoModes(getBaseContext());
            }
            else if(modes==null && endless){
                tonics = listGen.genNoModes(getBaseContext());
            }
            else if(modes.size()==1 && !endless) {
                tonics = listGen.genSingleMode(getBaseContext() ,numScales);
            }
            else if(!endless){
                tonics = listGen.genMultiMode(getBaseContext(),modes ,numScales);
            }else{
                tonics = listGen.genMultiMode(getBaseContext(),modes,24);
                numScales=1;
            }

        }

        tonicBox.setText(tonics.get(scaleIndex));

    }

    public void nonEndlessClick(){
        numScales -=1;
        //checks the number of scales that are left if none, ends the activity
        if(numScales==0){
            finish();
        }else{
            //sets the text of the boxes to be the next scale
            tonicBox.setText(tonics.get(scaleIndex));
            scaleBox.setText("Scales Remaining: "+ String.valueOf(numScales));
        }
    }

    public void endlessClick(){
        //duplicate check
        if(tonics.get(scaleIndex).equals(lastScale)){
            scaleIndex++;
        }
        //sets the tonics box to be the text of the scale
        tonicBox.setText(tonics.get(scaleIndex));
        //checks whether the index is too big for the array, and if so resets to get a new array list
        if(scaleIndex==tonics.size()-1){
            tonics = listGen.genMultiMode(getBaseContext(),modes,24);
            scaleIndex=0;
        }
        lastScale = tonics.get(scaleIndex);
        numScales++;
        scaleBox.setText(numScales+" scales completed");

    }


    public void clickNextScale(View view){
        scaleIndex+=1;
        if(!endless){
            nonEndlessClick();
        }else{
            endlessClick();
        }
    }


}
