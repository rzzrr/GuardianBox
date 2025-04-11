package com.rzatha.guardianbox.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.usecases.DeleteFolder;
import com.rzatha.guardianbox.domain.usecases.DeleteLogin;
import com.rzatha.guardianbox.domain.usecases.DeleteNote;
import com.rzatha.guardianbox.domain.usecases.GetLogins;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.usecases.InsertFolder;
import com.rzatha.guardianbox.domain.usecases.InsertLogin;
import com.rzatha.guardianbox.domain.usecases.InsertNote;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class RecordViewModel extends AndroidViewModel {

    private Application application;
    private RepositoryImpl repository = new RepositoryImpl(application);

    public RecordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Login>> getLogins() {
        return new GetLogins(repository).getLogins();
    }

    public Completable insertLogin(Login login){
        return new InsertLogin(repository).insertLogin(login);
    }

    public Completable updateLogin(Login login) {
        return new InsertLogin(repository).insertLogin(login);
    }

    public Completable deleteLogin(Login login) {
        return new DeleteLogin(repository).deleteLogin(login);
    }


    public Completable insertNote(Note login){
        return new InsertNote(repository).insertNote(login);
    }

    public Completable updateNote(Note login) {
        return new InsertNote(repository).insertNote(login);
    }

    public Completable deleteNote(Note login) {
        return new DeleteNote(repository).deleteNote(login);
    }


    public Completable insertFolder(Folder login){
        return new InsertFolder(repository).insertFolder(login);
    }

    public Completable updateFolder(Folder login) {
        return new InsertFolder(repository).insertFolder(login);
    }

    public Completable deleteFolder(Folder login) {
        return new DeleteFolder(repository).deleteFolder(login);
    }

}
