package com.example.fussionv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnMasuk;
    private ProgressBar pbLogin;
    private DBHelper dbHelper;
    private SessionManager sessionManager;
    private TextView tvDaftar, lupaPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnMasuk = findViewById(R.id.btnMasuk);
        tvDaftar = findViewById(R.id.tvDaftar);
        lupaPassword = findViewById(R.id.lupaPassword);
        pbLogin = findViewById(R.id.pbLogin);

        dbHelper = new DBHelper(this);
        sessionManager = new SessionManager(this);

        if (sessionManager.loggedIn()) {
            startActivity(new Intent(LoginActivity.this, ChoiceActivity.class));
        }

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        lupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });

//        btnMasuk.setOnClickListener(v -> {
//            if (etEmail.getText().length() > 0 && etPassword.getText().length() > 0) {
//                login(etEmail.getText().toString(), etPassword.getText().toString());
//            } else {
//                Toast.makeText(getApplicationContext(), "Silakan isi semua data", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                Boolean res = dbHelper.checkUser(email, password);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    if (res == true) {
                        sessionManager.setLoggedIn(true);
                        Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ChoiceActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}