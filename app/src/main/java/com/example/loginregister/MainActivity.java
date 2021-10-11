package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginregister.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DatabaseHelper db;
    String email;
    String password;
    String name;
    Boolean checkEmail;
    Boolean insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.etName.getText().toString();
                email = binding.etEmail.getText().toString();
                password = binding.etPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all the form", Toast.LENGTH_SHORT).show();
                } else {
                    checkEmail = db.checkEmail(email);
                    if (!checkEmail) {
                        insert = db.insertData(name, email, password);
                        if (insert) {
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User existed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}