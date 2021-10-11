package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginregister.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    SharedPreferences sp;
    private static final String SHARED_PREF_NAME = "mrdpref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        email = sp.getString(KEY_EMAIL, null);
        if (email != null) {
            binding.txtDummy.setText(email);
        }

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(HomeActivity.this, "Log out successfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}