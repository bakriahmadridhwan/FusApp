package com.example.fussionv2.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Topik {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "topik")
    public String namatopik;

    public String deskripsi;



    public String getNamatopik() {
        return namatopik;
    }

    public void setNamatopik(String namatopik) {
        this.namatopik = namatopik;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
