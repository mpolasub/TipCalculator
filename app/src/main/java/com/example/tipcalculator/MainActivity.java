package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enteredAmt;
    private SeekBar seekBar;
    private Button calcButton;
    private TextView resultView;
    private TextView tipPercentage;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmt = (EditText) findViewById(R.id.editTextId);
        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        calcButton = (Button) findViewById(R.id.calculateId);
        resultView = (TextView) findViewById(R.id.responseId);
        tipPercentage = (TextView) findViewById(R.id.tipPercentageId);
        totalView = (TextView) findViewById(R.id.totalId);

        calcButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage.setText(String.valueOf("Tip Percentage: " + seekBar.getProgress() + "%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        calculate();
    }

    public void calculate() {
        float result = 0.0f;

        if(!enteredAmt.getText().toString().equals("")) {
            enteredBillFloat = Float.parseFloat(enteredAmt.getText().toString());
            result = (float) (enteredBillFloat*seekBarPercentage*0.01);
            resultView.setText("Your tip will be $"+String.valueOf(result));
            totalView.setText("Your total bill is $"+String.valueOf(result+enteredBillFloat));
        }
        else {
            Toast.makeText(MainActivity.this, "Please enter a bill amount.", Toast.LENGTH_LONG).show();
        }

    }
}