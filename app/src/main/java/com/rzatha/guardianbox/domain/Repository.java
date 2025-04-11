package com.rzatha.guardianbox.domain;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Folder;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface Repository {
    LiveData<List<Note>> getNotes();

    LiveData<List<Folder>> getFolders();

    LiveData<List<Login>> getLogins();

    Completable insertLogin(Login login);
    Completable insertNote(Note note);
    Completable insertFolder(Folder folder);

    Completable deleteLogin(Login login);
    Completable deleteNote(Note note);
    Completable deleteFolder(Folder folder);

    Completable updateLogin(Login login);
    Completable updateNote(Note note);
    Completable updateFolder(Folder folder);


}
