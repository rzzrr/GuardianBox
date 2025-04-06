package com.rzatha.guardianbox.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;

import java.util.List;

@Dao
interface RecordDao {

    @Query("SELECT * FROM login")
    LiveData<List<Login>> getAllLogins();

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM folder")
    LiveData<List<Folder>> getAllFolders();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLogin(Login login);

    @Delete
    void deleteLogin(Login login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFolder(Folder folder);

    @Delete
    void deleteFolder(Folder folder);
}
