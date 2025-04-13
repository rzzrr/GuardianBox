package com.rzatha.guardianbox.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class FolderDbModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    public FolderDbModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public FolderDbModel(String name){
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
