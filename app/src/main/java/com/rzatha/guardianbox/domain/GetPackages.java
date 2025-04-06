package com.rzatha.guardianbox.domain;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Package;

import java.util.List;

public class GetPackages {
    private final Repository repository;
    public GetPackages (Repository repository) {
        this.repository = repository;
    }
    public LiveData<List<Package>> getPackages() {
        return repository.getPackages();
    }
}
