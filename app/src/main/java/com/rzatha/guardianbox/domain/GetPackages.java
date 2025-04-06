package com.rzatha.guardianbox.domain;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.model.Folder;

import java.util.List;

public class GetPackages {
    private final Repository repository;
    public GetPackages (Repository repository) {
        this.repository = repository;
    }
    public LiveData<List<Folder>> getPackages() {
        return repository.getPackages();
    }
}
