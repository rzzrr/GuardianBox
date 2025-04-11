package com.rzatha.guardianbox.domain.usecases;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;

import io.reactivex.rxjava3.core.Completable;

public class InsertFolder {
    private final Repository repository;

    public InsertFolder(Repository repository) {
        this.repository = repository;
    }

    public Completable insertFolder(Folder folder) {
        return repository.insertFolder(folder);
    }

}
