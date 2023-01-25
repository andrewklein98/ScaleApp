package com.example.scaleapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PracticingActivity extends AppCompatActivity {

    TextView scaleBox;
    TextView tonicBox;
    ImageView modeImage;

    ListGenerator listGen;
    ImageGeneration imgGen;

    ArrayList<String[]> scales;
    ArrayList<String> modes;

    String lastScale;

    int numScales;
    int tonicIndex = 0;

    boolean metronome;
    boolean endless;
    boolean noModes=false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicing);
        listGen = new ListGenerator();
        imgGen = new ImageGeneration(getBaseContext());
        modeImage = findViewById(R.id.scaleImage);
        tonicBox = (TextView) findViewById(R.id.tonicBox);
        //gets the text boc for the number of scales
        scaleBox = (TextView) findViewById(R.id.numScaleText);
        //gets the values that we passed into the activity when it was created
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //gets all the values passed in from the previous activity
            //checks if the metronome is on
            metronome = bundle.getBoolean("metronomeOn");
            modes = bundle.getStringArrayList("modes");
            endless = bundle.getBoolean("endless");
            numScales = bundle.getInt("numScales");

            if (modes==null || modes.size()<=0 ){
                noModes=true;
            }
            //checks if endless mode is on, if so gets the numScales
            if (!endless) {
                nonEndlessCreate();
            } else {
                endlessCreate(bundle);
            }
        }
        tonicBox.setText(scales.get(tonicIndex)[0]+" "+scales.get(tonicIndex)[1]);
        //sets the key signature based on the scale and tonality
        modeImage.setImageDrawable(imgGen.combineImages(scales.get(tonicIndex)[0],scales.get(tonicIndex)[1]));
    }

    public void endlessCreate(Bundle bundle){

        scaleBox.setText("0 scales completed");
        if(noModes){
            scales =listGen.genNoModes(getBaseContext());
        } else {
            scales = listGen.genMultiMode(getBaseContext(), modes, 24);
        }
        numScales=0;
    }

    public void nonEndlessCreate(){
        scaleBox.setText(String.valueOf(numScales)  + " Scales Remaining");
        if(noModes){
            scales = listGen.genNoModes(getBaseContext());
        }
        else{
            scales = listGen.genMultiMode(getBaseContext(),modes ,numScales);
        }
    }

    public void nonEndlessClick(){
        numScales -=1;
        //checks the number of scales that are left if none, ends the activity
        if(numScales==0){
            finish();
        }else{
            //checks if the index is too high for the tonic array
            if(tonicIndex >=12){
                tonicIndex = 0;
            }
            //sets the text of the boxes to be the next scale
            tonicBox.setText(getScale(scales.get(tonicIndex)));
            scaleBox.setText(String.valueOf(numScales)+" Scales Remaining");
            modeImage.setImageDrawable(imgGen.combineImages(scales.get(tonicIndex)[0],scales.get(tonicIndex)[1]));
        }
    }

    public void endlessClick(){
        //duplicate check
        if(scales.get(tonicIndex).equals(lastScale)){
            tonicIndex++;
        }
        //sets the tonics box to be the text of the scale
        tonicBox.setText(getScale(scales.get(tonicIndex)));;
        //checks whether the index is too big for the array, and if so resets to get a new array list
        if(tonicIndex == scales.size()-1){
            if(noModes){
                scales = listGen.genNoModes(getBaseContext());
            }
            else {
                scales = listGen.genMultiMode(getBaseContext(), modes, 24);
            }
            tonicIndex =0;
        }
        lastScale = scales.get(tonicIndex)[0];
        numScales++;
        scaleBox.setText(numScales+" scales completed");
        modeImage.setImageDrawable(imgGen.combineImages(scales.get(tonicIndex)[0],scales.get(tonicIndex)[1]));

    }


    public void clickNextScale(View view){
        tonicIndex +=1;
        if(!endless){
            nonEndlessClick();
        }else{
            endlessClick();
        }
    }

    private String getScale(String[] scales){
        return scales[0]+" "+scales[1];
    }

}
