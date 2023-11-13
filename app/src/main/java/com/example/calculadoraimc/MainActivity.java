package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextWeight;
    EditText editTextHeight;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.weightID);
        editTextHeight = findViewById(R.id.heightID);
        textViewResult = findViewById(R.id.resultID);
    }

    private Float calculateIMC(Float weight, Float height) {
        return (weight / (height * height)) * 10000;
    }

    public void calcButtonOnClick(View v) {
        Float weight = Float.parseFloat(editTextWeight.getText().toString());
        Float height = Float.parseFloat(editTextHeight.getText().toString());

        Float resultIMC = calculateIMC(weight, height);
        String result = String.format("%.2f", resultIMC);

        textViewResult.setText(result);
        showToast(resultIMC);
    }

    private void showToast(Float result) {
        if (result < 18.5) {
            Toast toast = Toast.makeText(this, "Abaixo do peso", Toast.LENGTH_LONG);
            toast.show();
        } else if (result >= 18.5 && result <= 24.9) {
            Toast toast = Toast.makeText(this, "Peso normal", Toast.LENGTH_LONG);
            toast.show();
        } else if (result >= 25.0 && result <= 29.9) {
            Toast toast = Toast.makeText(this, "Pré-obesidade", Toast.LENGTH_LONG);
            toast.show();
        } else if (result >= 30.0 && result <= 34.9) {
            Toast toast = Toast.makeText(this, "Obesidade Grau 1", Toast.LENGTH_LONG);
            toast.show();
        } else if (result >= 35.0 && result <= 39.9) {
            Toast toast = Toast.makeText(this, "Obesidade Grau 2", Toast.LENGTH_LONG);
            toast.show();
        } else if (result >= 40) {
            Toast toast = Toast.makeText(this, "Obesidade Grau 3", Toast.LENGTH_LONG);
            toast.show();
        }
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