package com.rzatha.guardianbox.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.rzatha.guardianbox.data.Mapper;
import com.rzatha.guardianbox.data.database.AppDatabase;
import com.rzatha.guardianbox.data.database.RecordDao;
import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class RepositoryImpl implements Repository {

    private RecordDao recordDao;

    public RepositoryImpl(Application application) {

        recordDao = AppDatabase.getInstance(application).recordDao();

    }

    @Override
    public Completable insertLogin(Login login) {
        return recordDao.insertLogin(Mapper.mapLoginToLoginDbModel(login));
    }

    @Override
    public Completable insertNote(Note note) {
        return recordDao.insertNote(Mapper.mapNoteToNoteDbModel(note));
    }

    @Override
    public Completable insertFolder(Folder folder) {
        return recordDao.insertFolder(Mapper.mapFolderToFolderDbModel(folder));
    }

    @Override
    public Completable deleteLogin(Login login) {
        return recordDao.deleteLogin(Mapper.mapLoginToLoginDbModel(login));
    }

    @Override
    public Completable deleteNote(Note note) {
        return recordDao.deleteNote(Mapper.mapNoteToNoteDbModel(note));
    }

    @Override
    public Completable deleteFolder(Folder folder) {
        return recordDao.deleteFolder(Mapper.mapFolderToFolderDbModel(folder));
    }

    @Override
    public Completable updateLogin(Login login) {
        return recordDao.insertLogin(Mapper.mapLoginToLoginDbModel(login));
    }

    @Override
    public Completable updateNote(Note note) {
        return recordDao.insertNote(Mapper.mapNoteToNoteDbModel(note));
    }

    @Override
    public Completable updateFolder(Folder folder) {
        return recordDao.insertFolder(Mapper.mapFolderToFolderDbModel(folder));
    }

    @Override
    public LiveData<List<Note>> getNotes() {
        return Transformations.map(
                recordDao.getAllNotes(),
                Mapper::mapListNoteDbModelToListNote
        );
    }

    @Override
    public LiveData<List<Folder>> getFolders() {
        return Transformations.map(
                recordDao.getAllFolders(),
                Mapper::mapListFolderDbModelToListFolder
        );
    }

    @Override
    public LiveData<List<Login>> getLogins() {
        return Transformations.map(
                recordDao.getAllLogins(),
                Mapper::mapListLoginDbModelToListLogin
        );
    }
}
