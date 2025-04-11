package com.rzatha.guardianbox.domain.usecases;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;

import io.reactivex.rxjava3.core.Completable;

public class InsertLogin {
    private final Repository repository;

    public InsertLogin(Repository repository) {
        this.repository = repository;
    }

    public Completable insertLogin(Login login) {
        return repository.insertLogin(login);
    }
}
