package com.example.fussionv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etPassword, etPasswordConf;
    private Button btnSubmitDaftar;
    private TextView tvMasuk;
    DBHelper dbHelper;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // backLogin = findViewById(R.id.backLogin);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConf = findViewById(R.id.etPasswordConf);
        btnSubmitDaftar = findViewById(R.id.btnSubmitDaftar);
        tvMasuk = findViewById(R.id.tvMasuk);

        dbHelper = new DBHelper(this);

//        tvMasuk.setOnClickListener(v -> {
//            finish();
//        });

        tvMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnSubmitDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNama.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String passwordConf = etPasswordConf.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(passwordConf)) {
                    Toast.makeText(RegisterActivity.this, "Kata Sandi tidak sama!", Toast.LENGTH_SHORT).show();
                } else if (password.equals("") || email.equals("") || name.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    values.put(DBHelper.row_name, name);
                    values.put(DBHelper.row_email, email);
                    values.put(DBHelper.row_password, password);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Berhasil Daftar!, SILAHKAN LOGIN!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });
    }
}