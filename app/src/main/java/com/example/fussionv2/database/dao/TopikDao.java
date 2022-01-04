package com.example.fussionv2.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fussionv2.database.entitas.Topik;

import java.util.List;

@Dao
public interface TopikDao {

    @Query("SELECT * FROM topik")
    List<Topik> getAll();

    @Insert
    void insertAll(Topik... topiks);

    @Query("UPDATE topik SET topik=:topik, deskripsi=:deskripsi WHERE id=:id")
    void update(int id, String topik, String deskripsi);

    @Query("SELECT * FROM topik WHERE id=:id")
    Topik get(int id);

    @Delete
    void delete(Topik topik);

}
