package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginregister.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DatabaseHelper db;
    SharedPreferences sp;
    private static final String SHARED_PREF_NAME = "mrdpref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";

    String email;
    String password;
    Boolean checkEmailPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);
        sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.etEmail.getText().toString();
                password = binding.etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill all the form", Toast.LENGTH_SHORT).show();
                } else {
                    checkEmailPass = db.checkEmailPassword(email, password);

                    if (checkEmailPass) {
                        Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString(KEY_EMAIL, email);
                        editor.putString(KEY_PASS, password);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}