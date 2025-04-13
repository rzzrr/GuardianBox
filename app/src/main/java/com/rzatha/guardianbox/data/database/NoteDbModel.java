package com.rzatha.guardianbox.data.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteDbModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "text")
    private String text;

    public NoteDbModel(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    @Ignore
    public NoteDbModel( String name, String text) {
        this(0, name, text );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
