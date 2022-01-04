package com.example.fussionv2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fussionv2.adapter.TopikAdapter;
import com.example.fussionv2.database.AppDatabase;
import com.example.fussionv2.database.entitas.Topik;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PilihTopikActivity extends AppCompatActivity {

    private RecyclerView rvTopik;

    private AppDatabase database;
    private TopikAdapter topikAdapter;
    List<Topik> list;
    AlertDialog.Builder dialog;

    ImageButton addFav;

    ActionBar actionBar;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_topik);
        this.setTitle( "Daftar Topik");

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = new SearchView(this);

        //fabAdd = findViewById(R.id.fabAdd);
        ExtendedFloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        rvTopik = findViewById(R.id.rvTopik);
        database = AppDatabase.getInstance(getApplicationContext());
        list = new ArrayList<>();
        list.clear();
        list.addAll(database.topikDao().getAll());
        topikAdapter = new TopikAdapter(list, getApplicationContext());

        // ini langsung dari db masuk ke adapter
        //topikAdapter = new TopikAdapter(getApplicationContext(), database.topikDao().getAll());

        //dipindah masuk kelist dulu baru masuk ke adapter
        //topikAdapter = new TopikAdapter(database.topikDao().getAll(), getApplicationContext());

        topikAdapter.setDialog(new TopikAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus", "Komentar", "Simpan"};
                dialog = new AlertDialog.Builder(PilihTopikActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(PilihTopikActivity.this, AddTopikActivity.class);
                                intent.putExtra("id", list.get(position).id);
                                startActivity(intent);
                                break;
                            case 1:
                                Topik topik = list.get(position);
                                database.topikDao().delete(topik);
                                Toast.makeText(PilihTopikActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                onStart();
                                break;
                            case 2:
                                Toast.makeText(PilihTopikActivity.this, "Belum tersedia", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(PilihTopikActivity.this, "Belum juga hehe", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        rvTopik.setHasFixedSize(true);
        rvTopik.setLayoutManager(new LinearLayoutManager(this));
        rvTopik.setAdapter(topikAdapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PilihTopikActivity.this, AddTopikActivity.class);
                startActivity(intent);
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

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.topikDao().getAll());
        topikAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                topikAdapter.getFilter().filter(s.toString());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}