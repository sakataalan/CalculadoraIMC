package com.example.calculadoraimc.controller;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculadoraimc.R;
import com.example.calculadoraimc.model.UserDatabase;

import java.util.ArrayList;

public class HistoricActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> imc, date;
    HistoricAdapter historicAdapter;

    UserDatabase userDatabase = new UserDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_activity);
        imc = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview);
        historicAdapter = new HistoricAdapter(this, imc, date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = userDatabase.getdata();
        if (cursor.getCount()==0) {
            Toast.makeText(HistoricActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while(cursor.moveToNext()) {
                imc.add(cursor.getString(0));
                date.add(cursor.getString(1));
            }
        }
    }
}
