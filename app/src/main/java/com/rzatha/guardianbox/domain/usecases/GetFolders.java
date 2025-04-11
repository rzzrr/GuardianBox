package com.rzatha.guardianbox.domain.usecases;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;

import java.util.List;

public class GetFolders {
    private final Repository repository;
    public GetFolders(Repository repository) {
        this.repository = repository;
    }
    public LiveData<List<Folder>> getPackages() {
        return repository.getFolders();
    }
}
