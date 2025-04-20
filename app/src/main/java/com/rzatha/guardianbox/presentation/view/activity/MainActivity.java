package com.rzatha.guardianbox.presentation.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.databinding.ActivityMainBinding;
import com.rzatha.guardianbox.presentation.view.fragment.LoginEditFragment;
import com.rzatha.guardianbox.presentation.view.fragment.RecordFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launchFragment(LoginEditFragment.newInstance());


    }

    private void launchFragment(Fragment fragment){
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainerView.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}