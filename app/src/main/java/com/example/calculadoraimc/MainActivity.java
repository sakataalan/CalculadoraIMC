package com.example.myapp;

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