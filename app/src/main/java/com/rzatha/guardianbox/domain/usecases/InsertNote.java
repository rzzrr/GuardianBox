package com.rzatha.guardianbox.domain.usecases;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;

import io.reactivex.rxjava3.core.Completable;

public class InsertNote {
    private final Repository repository;

    public InsertNote(Repository repository) {
        this.repository = repository;
    }

    public Completable insertNote(Note note) {
        return repository.insertNote(note);
    }
}
