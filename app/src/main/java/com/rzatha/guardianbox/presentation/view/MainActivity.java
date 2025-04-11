package com.rzatha.guardianbox.presentation.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.R;
import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.databinding.ActivityMainBinding;
import com.rzatha.guardianbox.domain.Repository;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launchFragment(RecordFragment.newInstance());

        //TEST

        Repository repository = new RepositoryImpl(getApplication());
        binding.fabAddRecord.setOnClickListener(view -> {
            repository.insertLogin(new Login("1","rn", "login", "password"));
            repository.insertNote(new Note("2", "name note", "text note"));
            repository.insertFolder(new Folder("3", "folder name"));
        });

    }

    private void launchFragment(Fragment fragment){
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainerView.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}