package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextWeight;
    EditText editTextHeight;
    Button buttonCalculate;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.weightID);
        editTextHeight = findViewById(R.id.heightID);
        textViewResult = findViewById(R.id.resultID);

        buttonCalculate = findViewById(R.id.calcButtonID);
    }

    private Float calculateIMC(Float weight, Float height) {
        return (weight / height) * 100;
    }

    public void calcButtonOnClick(View v) {
        Float weight = Float.parseFloat(editTextWeight.getText().toString());
        Float height = Float.parseFloat(editTextHeight.getText().toString());

        Float result = calculateIMC(weight, height);

        textViewResult.setText(String.format("%.2f", result));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("LifeCicle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("LifeCicle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LifeCicle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf("LifeCicle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("LifeCicle", "onDestroy");
    }

}