package com.example.fussionv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity {

    private ImageView btnLogout, btnSetting;
    // private TextView valueName;
    // DBHelper dbHelper;

    private CardView cvTopik, cvCatatan, cvInfo, cvProfil, cvFav;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        btnLogout = findViewById(R.id.btnLogout);
        btnSetting = findViewById(R.id.btnSetting);
        cvTopik = findViewById(R.id.cvTopik);
        cvCatatan = findViewById(R.id.cvCatatan);
        cvInfo = findViewById(R.id.cvInfo);
        cvProfil = findViewById(R.id.cvProfil);
        cvFav = findViewById(R.id.cvFav);

        // dbHelper = new DBHelper(this);
        sessionManager = new SessionManager(this);

        if (!sessionManager.loggedIn()) {
            logout();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        cvTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, PilihTopikActivity.class);
                startActivity(intent);
            }
        });

        cvCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, CatatanActivity.class);
                startActivity(intent);
            }
        });

        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        cvProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChoiceActivity.this, "Belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        cvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChoiceActivity.this, "Belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void logout() {
        sessionManager.setLoggedIn(false);
        finish();
        startActivity(new Intent(ChoiceActivity.this, LoginActivity.class));
    }
}