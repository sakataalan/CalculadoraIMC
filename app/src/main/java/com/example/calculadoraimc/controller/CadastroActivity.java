package com.example.calculadoraimc.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculadoraimc.R;
import com.example.calculadoraimc.model.UserDatabase;
import com.example.calculadoraimc.model.UserDetails;

public class CadastroActivity extends AppCompatActivity {

    UserDatabase userDatabase = new UserDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void loginButtonOnClick(View v) {
        EditText usernameID = findViewById(R.id.cadastroUserID);
        EditText passwordID = findViewById(R.id.cadastroPassID);

        String username = usernameID.getText().toString();
        String password = passwordID.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(CadastroActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        } else {
            Boolean insertData = userDatabase.insertData(new UserDetails(username, password));

            if (insertData) {
                Toast.makeText(CadastroActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
                builder.setTitle(getString(R.string.error));
                builder.setMessage(getString(R.string.error_signup));
                builder.setPositiveButton(android.R.string.ok, null);
                builder.create().show();
            }
        }
    }
}