package com.example.scaleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PracticingActivity extends AppCompatActivity {
    int numScales;
    TextView scaleBox;
    TextView tonicBox;
    ListGenerator listGen;
    ArrayList<String> tonics;
    int scaleIndex =0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicing);
        listGen = new ListGenerator();

        //gets the text box for the metronome field
        EditText metBox = (EditText) findViewById(R.id.textEdit);
        tonicBox = (TextView) findViewById(R.id.tonicBox);
        //gets the text boc for the number of scales
        scaleBox= (TextView) findViewById(R.id.numScaleText);
        //gets the values that we passed into the activity when it was created
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            boolean useMetronome = bundle.getBoolean("metronomeOn");
            if(useMetronome){
                metBox.setText("Metronome On");
            }
            else {
                metBox.setText("Metronome Off");
            }
            numScales = bundle.getInt("numScales");
            scaleBox.setText(String.valueOf(numScales));
        }
        tonics = listGen.genSingleMode(getBaseContext(),numScales);
        tonicBox.setText(tonics.get(scaleIndex));

    }
    public void clickNextScale(View view){
        numScales -=1;
        scaleIndex+=1;
        if(numScales==0){
            finish();
        }else{
            tonicBox.setText(tonics.get(scaleIndex));
            scaleBox.setText(String.valueOf(numScales));
        }
    }


}
