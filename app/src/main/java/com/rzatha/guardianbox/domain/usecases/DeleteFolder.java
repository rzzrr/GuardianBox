package com.rzatha.guardianbox.domain.usecases;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;

import io.reactivex.rxjava3.core.Completable;

public class DeleteFolder {
    private final Repository repository;

    public DeleteFolder(Repository repository) {
        this.repository = repository;
    }

    public Completable deleteFolder(Folder folder) {
        return repository.deleteFolder(folder);
    }
}
