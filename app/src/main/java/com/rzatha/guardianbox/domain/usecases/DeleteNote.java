package com.rzatha.guardianbox.domain.usecases;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;

import io.reactivex.rxjava3.core.Completable;

public class DeleteNote {
    private final Repository repository;

    public DeleteNote(Repository repository) {
        this.repository = repository;
    }

    public Completable deleteNote(Note note) {
        return repository.deleteNote(note);
    }
}
