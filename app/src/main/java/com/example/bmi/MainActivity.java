package com.example.bmi;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SeekBar weightSeekBar, heightSeekBar;
    private TextView weightTextValue, heightTextValue, resultText;
    private Button calculateButton;

    private final int step = 1;
    private final int weightMin = 40;
    private final int heightMin = 130;

    private int weightValue = weightMin;
    private int heightValue = heightMin;

    private double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewUnits();
        launchSeekListener();
    }

    private void getViewUnits() {
        weightSeekBar = findViewById(R.id.weightSeekBar);
        heightSeekBar = findViewById(R.id.heightSeekBar);

        calculateButton = findViewById(R.id.calculateButton);

        weightTextValue = findViewById(R.id.weightValueText);
        weightTextValue.setText("40 KG");

        heightTextValue = findViewById(R.id.heightValueText);
        heightTextValue.setText("130 CM");

        resultText = findViewById(R.id.resultText);

        int weightMax = 220;
        weightSeekBar.setMax((weightMax - weightMin) / step);
        int heightMax = 220;
        heightSeekBar.setMax((heightMax - heightMin) / step);
    }

    private void launchSeekListener() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int value = weightMin + (seekBar.getProgress() * step);
                weightTextValue.setText(Integer.toString(value) + " KG");
                weightValue = value;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int value = heightMin + (seekBar.getProgress() * step);
                heightTextValue.setText(Integer.toString(value) + " CM");
                heightValue = value;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onCalculate(View view) {
        if(result == 0){
            result = (weightValue / Math.pow( (double) heightValue / 100, 2));
            calculateButton.setText(R.string.reset);
            calculateButton.setBackgroundColor(getResources().getColor(R.color.teal_200));
            resultText.setText(getResults());
        }else{
            result = 0;
            calculateButton.setText(R.string.calculate);
            resultText.setText("");
            calculateButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        }
    }

    private String getResults(){
        if(result < 16.0)
            return "Underweight (Severe thinness)";
        else if((result >= 16.0) & (result <= 16.9))
            return "Underweight (Moderate thinness)";
        else if((result >= 17.0) & (result <= 18.4))
            return "Underweight (Mild thinness)";
        else if((result >= 18.5) & (result <= 24.9))
            return "Normal range";
        else if((result >= 25.0) & (result <= 29.9))
            return "Overweight (Pre-obese)";
        else if((result >= 30.0) & (result <= 34.9))
            return "Obese (Class I)";
        else if((result >= 35.0) & (result <= 39.9))
            return "Obese (Class II)";
        else if(result >= 40.0)
            return "Obese (Class III)";
        return "";
    }

}