package com.rzatha.guardianbox.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class FolderDbModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "name")
    private String name;

    public FolderDbModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
