package com.example.calculadoraimc.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadoraimc.R;
import com.example.calculadoraimc.model.UserDatabase;
import com.example.calculadoraimc.model.UserDetails;

public class LoginActivity extends AppCompatActivity {

    UserDatabase userDatabase = new UserDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void loginButtonOnClick(View v) {
        EditText usernameID = findViewById(R.id.usernameID);
        EditText passwordID = findViewById(R.id.passwordID);

        String username = usernameID.getText().toString();
        String password = passwordID.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        } else {
            Boolean checkCredentials = userDatabase.checkUsernamePassword(new UserDetails(username, password));

            if (checkCredentials) {
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle(getString(R.string.error));
                builder.setMessage(getString(R.string.wrong_user));
                builder.setPositiveButton(android.R.string.ok, null);
                builder.create().show();
            }
        }
    }

    public void cadastrarButtonOnClick(View v) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}
