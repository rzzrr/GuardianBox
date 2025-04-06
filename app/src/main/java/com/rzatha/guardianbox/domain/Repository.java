package com.rzatha.guardianbox.domain;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Package;

import java.util.List;

public interface Repository {
    LiveData<List<Note>> getNotes();

    LiveData<List<Package>> getPackages();

    LiveData<List<Login>> getLogins();
}
