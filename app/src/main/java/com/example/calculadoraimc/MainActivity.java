package com.example.calculadoraimc;

import androidx.appcompat.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCicle", "onCreate");

        final EditText editTextWeight = findViewById(R.id.editTextWeight);
        final EditText editTextHeight = findViewById(R.id.editTextHeight);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        final textView textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnclickListener(new View.oinClickListener()
            @Override
            public void onClick(View v) {
                String weightStr = editTextWeight.getText().toString();
                String heightStr = editTextHeight.getText().toString();

                if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                    float weight = Float.parseFloat(weightStr);
                    float height = Float.parseFloat(heightStr);

                    float imc = calculateIMC(weight, height);

                    textViewResult.setText(getString(R.string.result) + " " + imc);
                } else {
                    textViewResult.setText(getString(R.string.result) + " " + getString(R.string.calculation_error));
                }
            }
        });
    }

    private float calculateIMC(float weight, float height) {
        return weight / (height * height);
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