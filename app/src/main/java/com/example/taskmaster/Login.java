package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void login(View view) {
        Intent SplashScreen = new Intent(this, SpActivity.class);
        String userNameText = username.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        if (userNameText.equals("admin") && passwordText.equals("admin")) {
            Toast.makeText(this, "Acceso correcto", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, SpActivity.class));
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}