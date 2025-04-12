package com.rzatha.guardianbox.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.usecases.DeleteFolder;
import com.rzatha.guardianbox.domain.usecases.DeleteLogin;
import com.rzatha.guardianbox.domain.usecases.DeleteNote;
import com.rzatha.guardianbox.domain.usecases.GetLogins;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.domain.usecases.InsertFolder;
import com.rzatha.guardianbox.domain.usecases.InsertLogin;
import com.rzatha.guardianbox.domain.usecases.InsertNote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class RecordViewModel extends AndroidViewModel {

    private Application application;
    private final RepositoryImpl repository;

    public MediatorLiveData<List<Record>> ldRecords = new MediatorLiveData<>();
    private final LiveData<List<Note>> allNotes;
    private final LiveData<List<Login>> allLogins;
    private final LiveData<List<Folder>> allFolders;


    private List<Note> lastNotes = new ArrayList<>();
    private List<Login> lastLogins = new ArrayList<>();
    private List<Folder> lastFolders = new ArrayList<>();


    public RecordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;

        repository = new RepositoryImpl(application);

        allNotes = repository.getNotes();
        allLogins = repository.getLogins();
        allFolders = repository.getFolders();

        ldRecords.addSource(allLogins, logins -> {
            if (logins != null) lastLogins = logins;
            updateCombines();
        });

        ldRecords.addSource(allNotes, notes -> {
                    if (notes != null) lastNotes = notes;
                    updateCombines();
                }
        );

        ldRecords.addSource(allFolders, folders -> {
                    if (folders != null) lastFolders = folders;
                    updateCombines();
                }
        );
    }

    private void updateCombines() {
        List<Record> combined = new ArrayList<>();
        combined.addAll(lastNotes);
        combined.addAll(lastLogins);
        combined.addAll(lastFolders);
        ldRecords.postValue(combined);
    }


    public Completable insertLogin(Login login) {
        return new InsertLogin(repository).insertLogin(login);
    }

    public Completable updateLogin(Login login) {
        return new InsertLogin(repository).insertLogin(login);
    }

    public Completable deleteLogin(Login login) {
        return new DeleteLogin(repository).deleteLogin(login);
    }


    public Completable insertNote(Note login) {
        return new InsertNote(repository).insertNote(login);
    }

    public Completable updateNote(Note login) {
        return new InsertNote(repository).insertNote(login);
    }

    public Completable deleteNote(Note login) {
        return new DeleteNote(repository).deleteNote(login);
    }


    public Completable insertFolder(Folder login) {
        return new InsertFolder(repository).insertFolder(login);
    }

    public Completable updateFolder(Folder login) {
        return new InsertFolder(repository).insertFolder(login);
    }

    public Completable deleteFolder(Folder login) {
        return new DeleteFolder(repository).deleteFolder(login);
    }

}
