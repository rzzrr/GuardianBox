package com.rzatha.guardianbox.domain.usecases;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Login;

import java.util.List;

public class GetLogins {
    private final Repository repository;
    public GetLogins (Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Login>> getLogins() {
        return repository.getLogins();
    }
}
