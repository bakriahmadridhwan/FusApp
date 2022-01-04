package com.example.fussionv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fussionv2.database.AppDatabase;
import com.example.fussionv2.database.entitas.Topik;

import butterknife.ButterKnife;

public class AddTopikActivity extends AppCompatActivity {

    private EditText edtNamaTopikAdd, edtNamaDeskripsiAdd;
    private Button btnSimpanAdd;

    private AppDatabase database;
    int id = 0;
    boolean isEdit = false;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topik);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNamaTopikAdd = findViewById(R.id.edtNamaTopikAdd);
        edtNamaDeskripsiAdd = findViewById(R.id.edtNamaDeskripsiAdd);
        btnSimpanAdd = findViewById(R.id.btnSimpanAdd);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id > 0) {
            isEdit = true;
            this.setTitle("Ubah Topik");

            Topik topik = database.topikDao().get(id);
            edtNamaTopikAdd.setText(topik.namatopik);
            edtNamaDeskripsiAdd.setText(topik.deskripsi);
        } else {
            isEdit = false;
            this.setTitle("Tambah Topik");
        }

        btnSimpanAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Topik topik = new Topik();
                topik.namatopik = edtNamaTopikAdd.getText().toString();
                topik.deskripsi = edtNamaDeskripsiAdd.getText().toString();

                String namaTopik = edtNamaTopikAdd.getText().toString().trim();
                String namaDeskripsi = edtNamaDeskripsiAdd.getText().toString().trim();

                if (isEdit) {
                    database.topikDao().update(id, topik.namatopik, topik.deskripsi);
                    Toast.makeText(AddTopikActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (namaTopik.equals("") || namaDeskripsi.equals("")) {
                        Toast.makeText(AddTopikActivity.this, "data harus diisi", Toast.LENGTH_SHORT).show();
                    } else {
                        database.topikDao().insertAll(topik);
                        Toast.makeText(AddTopikActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}