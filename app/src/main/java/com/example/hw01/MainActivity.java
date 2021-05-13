package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import static java.lang.Double.parseDouble;



public class MainActivity extends AppCompatActivity {

    SeekBar tipSeekBar;
    TextView viewProgress, tipAmount, totalAmount;
    Button exitButton;
    EditText editBillAmount;
    double valueTip, valueTotal;
    int customTipProgress, checkedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipSeekBar = findViewById(R.id.tipSeekBar);
        viewProgress = findViewById(R.id.viewProgress);
        exitButton = findViewById(R.id.exitButton);
        editBillAmount = findViewById(R.id.editBillAmount);
        tipAmount = findViewById(R.id.tipAmount);
        totalAmount = findViewById(R.id.totalAmount);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        customTipProgress = 20;
        tipSeekBar.setEnabled(false);


        //Listener function to check every keystroke and render output based on the input
        editBillAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkedId = radioGroup.getCheckedRadioButtonId();
                onChange(checkedId);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



        //Listener function to check change in radio button selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onChange(checkedId);
            }
        });


        //Function to update value of progress when seek bar value is changed
        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewProgress.setText(String.valueOf(progress));
                customTipProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(editBillAmount.getText().toString().isEmpty()){
                    tipAmount.setText("");
                    totalAmount.setText("");
                }
                else{
                    valueTip = parseDouble(editBillAmount.getText().toString()) * customTipProgress / 100;
                    tipAmount.setText(String.format("%.2f", valueTip));
                    valueTotal = parseDouble(editBillAmount.getText().toString()) + valueTip;
                    totalAmount.setText(String.format("%.2f", totalAmount));
                }
            }
        });


        //Button logic to exit the application when clicked
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }



    //Listener function to check change in the radio button ID
    private void onChange(int checkedId) {
        if(checkedId == R.id.tenPercent){
            tipSeekBar.setEnabled(false);
            if(editBillAmount.getText().toString().isEmpty()){
                tipAmount.setText("");
                totalAmount.setText("");
            }
            else{
                valueTip = parseDouble(editBillAmount.getText().toString()) * 0.1;
                tipAmount.setText(String.format("%.2f", valueTip));
                valueTotal = parseDouble(editBillAmount.getText().toString()) + valueTip;
                totalAmount.setText(String.format("%.2f", totalAmount));
            }
        }
        else if(checkedId == R.id.fifteenPercent){
            tipSeekBar.setEnabled(false);
            if(editBillAmount.getText().toString().isEmpty()){
                tipAmount.setText("");
                totalAmount.setText("");
            }
            else{
                valueTip = parseDouble(editBillAmount.getText().toString()) * 0.15;
                tipAmount.setText(String.format("%.2f", valueTip));
                valueTotal = parseDouble(editBillAmount.getText().toString()) + valueTip;
                totalAmount.setText(String.format("%.2f", totalAmount));
            }
        }
        else if(checkedId == R.id.eighteenPercent){
            tipSeekBar.setEnabled(false);
            if(editBillAmount.getText().toString().isEmpty()){
                tipAmount.setText("");
                totalAmount.setText("");
            }
            else{
                valueTip = parseDouble(editBillAmount.getText().toString()) * 0.180;
                tipAmount.setText(String.format("%.2f", valueTip));
                valueTotal = parseDouble(editBillAmount.getText().toString()) + valueTip;
                totalAmount.setText(String.format("%.2f", totalAmount));
            }
        }
        else if(checkedId == R.id.customTip){
            tipSeekBar.setEnabled(true);
            if(editBillAmount.getText().toString().isEmpty()){
                tipAmount.setText("");
                totalAmount.setText("");
            }
            else{
                valueTip = parseDouble(editBillAmount.getText().toString()) * customTipProgress / 100;
                tipAmount.setText(String.format("%.2f", valueTip));
                valueTotal = parseDouble(editBillAmount.getText().toString()) + valueTip;
                totalAmount.setText(String.format("%.2f", totalAmount));
            }
        }
    }
}