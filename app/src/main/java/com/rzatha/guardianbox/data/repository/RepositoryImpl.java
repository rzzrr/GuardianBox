package com.rzatha.guardianbox.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.data.database.AppDatabase;
import com.rzatha.guardianbox.data.database.RecordDao;
import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    public List<Record> testList = new ArrayList<>();
    private RecordDao recordDao;

    public RepositoryImpl(Application application) {
        recordDao = AppDatabase.getInstance(application).recordDao();

        for (int i = 0; i < 5; i++) {
            Login login = new Login(i+ "", i + " login resource", i + " login", i + " pass");
            testList.add(login);
            testList.add(new Note(i+ "", i + " note name", i + " text"));
            testList.add(new Folder(i+ "", i + " folder name"));
        }

    }

    @Override
    public LiveData<List<Note>> getNotes() {
        return null;
    }

    @Override
    public LiveData<List<Folder>> getPackages() {
        return null;
    }

    @Override
    public LiveData<List<Login>> getLogins() {
        return recordDao.getAllLogins();
    }
}
