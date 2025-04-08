package com.rzatha.guardianbox.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.R;
import com.rzatha.guardianbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launchFragment(RecordFragment.newInstance());
    }

    private void launchFragment(Fragment fragment){
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainerView.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
}