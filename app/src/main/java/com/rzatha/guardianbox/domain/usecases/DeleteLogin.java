package com.rzatha.guardianbox.domain.usecases;

import android.util.Log;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;

import io.reactivex.rxjava3.core.Completable;

public class DeleteLogin {
    private final Repository repository;

    public DeleteLogin(Repository repository) {
        this.repository = repository;
    }

    public Completable deleteLogin(Login login) {
        return repository.deleteLogin(login);
    }
}
