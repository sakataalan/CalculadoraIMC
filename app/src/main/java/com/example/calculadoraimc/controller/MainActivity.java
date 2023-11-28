package com.example.calculadoraimc.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadoraimc.R;
import com.example.calculadoraimc.model.UserDatabase;
import com.example.calculadoraimc.model.UserDetails;

public class MainActivity extends AppCompatActivity {

    UserDatabase userDatabase = new UserDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Float calculateIMC(Float weight, Float height) {
        return (weight / (height * height)) * 10000;
    }

    public void calcButtonOnClick(View v) {
        EditText editTextWeight = findViewById(R.id.weightID);
        EditText editTextHeight = findViewById(R.id.heightID);
        TextView textViewResult = findViewById(R.id.resultID);

        Float weight = Float.parseFloat(editTextWeight.getText().toString());
        Float height = Float.parseFloat(editTextHeight.getText().toString());

        Float resultIMC = calculateIMC(weight, height);
        String result = String.format("%.2f", resultIMC);

        Boolean insertData = userDatabase.insertImc(result);

        if (insertData) {
            Toast.makeText(MainActivity.this, "Add to historic", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage(getString(R.string.addHistoricError));
            builder.setPositiveButton(android.R.string.ok, null);
            builder.create().show();
        }

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

    public void historicoButtonOnClick(View v) {
        Intent intent = new Intent(MainActivity.this, HistoricActivity.class);
        startActivity(intent);
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