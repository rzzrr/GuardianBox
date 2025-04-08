package com.rzatha.guardianbox.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.domain.GetLogins;
import com.rzatha.guardianbox.domain.model.Login;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private Application application;
    private RepositoryImpl repository = new RepositoryImpl(application);
    private GetLogins getLoginsUseCase = new GetLogins(repository);

    public RecordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Login>> getLogins() {
        return getLoginsUseCase.getLogins();
    }


}
