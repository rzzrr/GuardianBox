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

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface RecordDao {

    @Query("SELECT * FROM login")
    LiveData<List<LoginDbModel>> getAllLogins();

    @Query("SELECT * FROM note")
    LiveData<List<NoteDbModel>> getAllNotes();

    @Query("SELECT * FROM folder")
    LiveData<List<FolderDbModel>> getAllFolders();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertLogin(LoginDbModel login);

    @Delete
    Completable deleteLogin(LoginDbModel login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNote(NoteDbModel note);

    @Delete
    Completable deleteNote(NoteDbModel note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFolder(FolderDbModel folder);

    @Delete
    Completable deleteFolder(FolderDbModel folder);
}
