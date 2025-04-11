package com.rzatha.guardianbox.domain.usecases;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Note;

import java.util.List;

public class GetNotes {
    private final Repository repository;
    public GetNotes(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Note>>  getNotes() {
        return repository.getNotes();
    }
}
