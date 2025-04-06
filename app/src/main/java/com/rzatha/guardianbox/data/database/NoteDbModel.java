package com.rzatha.guardianbox.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteDbModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "text")
    private String text;

    public NoteDbModel(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }
}
